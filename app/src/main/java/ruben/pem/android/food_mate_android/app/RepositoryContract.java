package ruben.pem.android.food_mate_android.app;

import java.util.Date;
import java.util.List;

import ruben.pem.android.food_mate_android.data.DiaryFood;
import ruben.pem.android.food_mate_android.data.FoodItem;

public interface RepositoryContract {

    void getDiaryFood(Date date, GetDiaryFoodCallback getDiaryFoodCallback);

    void getFoodList(GetFoodListCallback getFoodListCallback);

    void addFood(DiaryFood diaryFood, InsertNewFoodItem callback);

    void getFoodFromBarcode(String barcode, GetFoodFromBarcodeAPI getFoodFromBarcodeAPI);

    void deleteDiaryFood(DiaryFood tag, DeleteDiaryFoodCallback deleteDiaryFoodCallback);

    void loadFoodList(boolean b, FetchFoodListJSONCallback fetchFoodListJSONCallback);

    void updateDate(Date currentDay);

    Date getCurrentDate();

    void deleteFoodItem(FoodItem item, DeleteFoodItem deleteFoodItem);

    interface DeleteFoodItem {
        void onDeletedFood(FoodItem item);
    }

    interface FetchFoodListJSONCallback {
        void onJSONDataFetched(boolean error);
    }

    interface GetDiaryFoodCallback {
        void setDiaryFoodList(List<DiaryFood> list);
    }

    interface GetFoodListCallback {
        void setFoodList(List<FoodItem> list);
    }

    interface InsertNewFoodItem {
        void foodInserted();
    }
    
    interface GetFoodFromBarcodeAPI {
        void foodFoundAPI(String name, String calories, String prots, String carbs, String fats);
    }
    
    interface DeleteDiaryFoodCallback {
        void deletedDiaryFood();
    }
}
