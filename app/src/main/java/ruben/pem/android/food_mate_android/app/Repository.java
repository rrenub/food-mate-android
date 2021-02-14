package ruben.pem.android.food_mate_android.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ruben.pem.android.food_mate_android.data.DiaryFood;
import ruben.pem.android.food_mate_android.data.FoodItem;
import ruben.pem.android.food_mate_android.database.AppDatabase;
import ruben.pem.android.food_mate_android.database.DiaryFoodDao;
import ruben.pem.android.food_mate_android.database.FoodItemDao;

public class Repository implements RepositoryContract{
    private static final String TAG = Repository.class.getSimpleName();

    public static final String JSON_FILE_ENG = "foodlist_eng.json";
    public static final String JSON_FILE = "foodlist.json";
    public static final String JSON_ROOT = "foods";

    private static final String URL_API_BC = "https://world.openfoodfacts.org/api/v0/product/";
    public static final String DB_FILE = "foodmate.db";

    private Date currentDay;

    private Context context;
    private static Repository INSTANCE;

    private AppDatabase database;

    public static RepositoryContract getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new Repository(context);
        }

        return INSTANCE;
    }

    private Repository(Context context) {
        this.context = context;
        this.currentDay = new Date();

        database = Room.databaseBuilder(
                context, AppDatabase.class, DB_FILE
        ).build();

        //populateMockData();
    }

    private DiaryFoodDao getDiaryFoodDao() {
        return database.diaryFoodDao();
    }

    private FoodItemDao getFoodItemDao() {
        return database.foodItemDao();
    }

    @Override
    public void getDiaryFood(final Date date, final GetDiaryFoodCallback getDiaryFoodCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(getDiaryFoodCallback != null) {
                    //populateMockData();
                    // today
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(date);
                    // reset hour, minutes, seconds and millis
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    Date startOfDay = cal.getTime();

                    // next day
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    Date endOfDay = cal.getTime();

                    List<DiaryFood> currentDayFood = getDiaryFoodDao().findDiaryFoodBetweenDates(startOfDay, endOfDay);
                    Log.d(TAG, "getDiaryFood(): List size: "+ currentDayFood.size());
                    getDiaryFoodCallback.setDiaryFoodList(currentDayFood);
                }
            }
        });
    }

    @Override
    public void getFoodList(final GetFoodListCallback getFoodListCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(getFoodListCallback != null) {
                    //populateMockData();
                    //populateMockFoodList();
                    List<FoodItem> foodList = getFoodItemDao().loadDiaryFood();
                    Log.d(TAG, "getFoodList(): List size: "+ foodList.size());
                    getFoodListCallback.setFoodList(foodList);
                }
            }
        });
    }

    @Override
    public void addFood(final DiaryFood diaryFood, final InsertNewFoodItem callback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(callback != null) {
                    getDiaryFoodDao().insertDiaryFood(diaryFood);
                    List<FoodItem> list = getFoodItemDao().checkFoodItem(diaryFood.name);
                    if(list.isEmpty()) {
                        FoodItem foodItemToInsert = new FoodItem(diaryFood.name, diaryFood.calories, diaryFood.proteins, diaryFood.carbs, diaryFood.fats);
                        getFoodItemDao().insertFoodItem(foodItemToInsert);
                        Log.d(TAG, foodItemToInsert.toString());
                    }
                    callback.foodInserted();
                }
            }
        });
    }

    @Override
    public void getFoodFromBarcode(final String barcode, final GetFoodFromBarcodeAPI getFoodFromBarcodeAPI) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Realizando petición a la API con el barcode" + barcode);
                String url = URL_API_BC + barcode + ".json";

                try {
                    JSONObject json = new JSONObject(readUrl(url));
                    JSONObject productInfo = json.getJSONObject("product");
                    JSONObject nutriments = productInfo.getJSONObject("nutriments");

                    String name = productInfo.getString("product_name_fr");
                    String calories =  nutriments.getString("energy-kcal_100g");
                    String prots = nutriments.getString("proteins_100g");
                    String carbs = nutriments.getString("carbohydrates_100g");
                    String fats = nutriments.getString("fat_100g");

                    //Log data
                    Log.d(TAG, "CARBS:" + nutriments.getString("carbohydrates_100g"));
                    Log.d(TAG, "CALORIAS:" + nutriments.getString("energy-kcal_100g"));
                    Log.d(TAG, "PROTES:" + nutriments.getString("proteins_100g"));
                    Log.d(TAG, "GRASAS:" + nutriments.getString("fat_100g"));
                    Log.d(TAG, "NOMBRE DEL PRODUCTO:" + name);

                    getFoodFromBarcodeAPI.foodFoundAPI(name, calories, prots, carbs, fats);
                } catch (Exception e) {
                    Log.d(TAG, "ERROR: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void deleteDiaryFood(final DiaryFood tag, final DeleteDiaryFoodCallback deleteDiaryFoodCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(deleteDiaryFoodCallback != null) {
                    Log.d(TAG, tag.name);
                    getDiaryFoodDao().deleteDiaryFood(tag);
                    deleteDiaryFoodCallback.deletedDiaryFood();
                    Log.d(TAG, Locale.getDefault().getDisplayLanguage());
                }
            }
        });
    }

    @Override
    public void loadFoodList(boolean b, final FetchFoodListJSONCallback fetchFoodListJSONCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                /*if(clearFirst) {
                    database.clearAllTables();
                }*/

                boolean error = false;
                if(getFoodItemDao().loadDiaryFood().size() == 0 ) {
                    error = !loadCatalogFromJSON(loadJSONFromAsset());
                }

                if(fetchFoodListJSONCallback != null) {
                    fetchFoodListJSONCallback.onJSONDataFetched(error);
                }
            }
        });
    }

    private boolean loadCatalogFromJSON(String json) {
        Log.e(TAG, "loadCatalogFromJSON()");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);
            if (jsonArray.length() > 0) {
                final List<FoodItem> foodList = Arrays.asList(
                        gson.fromJson(jsonArray.toString(), FoodItem[].class)
                );

                for (FoodItem foodItem: foodList) {
                    getFoodItemDao().insertFoodItem(foodItem);
                }
                return true;
            }
        } catch (JSONException error) {
            Log.e(TAG, "error: " + error);
        }
        return false;
    }

    private String loadJSONFromAsset() {
        //Log.e(TAG, "loadJSONFromAsset()");

        String json = null;

        String jsonFile = JSON_FILE_ENG;
        if(Locale.getDefault().getDisplayLanguage().equals("español")) {
            jsonFile = JSON_FILE;
        }

        try {
            InputStream is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException error) {
            Log.e(TAG, "error: " + error);
        }

        return json;
    }

    @Override
    public void deleteFoodItem(FoodItem item, DeleteFoodItem deleteFoodItem) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(deleteFoodItem != null) {
                    getFoodItemDao().deleteFoodItem(item);
                    deleteFoodItem.onDeletedFood(item);
                }
            }
        });
    }

    @Override
    public void updateDate(Date currentDay) {
        this.currentDay = currentDay;
    }

    @Override
    public Date getCurrentDate() {
        return currentDay;
    }

    /*
    private void populateMockData() {
        getDiaryFoodDao().insertDiaryFood(new DiaryFood("Atún", 200, 200, new Date()));
    }

    private void populateMockFoodList() {
        getFoodItemDao().insertFoodItem(new FoodItem("Bacalao",200));
    }
    */


    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
