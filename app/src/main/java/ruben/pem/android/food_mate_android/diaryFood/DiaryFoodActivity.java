package ruben.pem.android.food_mate_android.diaryFood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ruben.pem.android.food_mate_android.DiaryFoodDetail.OnSwipeTouchListener;
import ruben.pem.android.food_mate_android.R;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodActivity
        extends AppCompatActivity implements DiaryFoodContract.View {

    public static String TAG = DiaryFoodActivity.class.getSimpleName();

    private DiaryFoodContract.Presenter presenter;

    private TextView currentDayText, calories, prots, carbs, fats;
    private DiaryFoodAdapter listAdapter;
    private Toolbar toolbar;
    private RecyclerView listView;
    private MaterialButton addFoodBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_food);

        toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.diary_food_title);

        listAdapter = new DiaryFoodAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaryFood item = (DiaryFood) view.getTag();
                Log.d(TAG, "onClick(): " + item.name);
                presenter.onDiaryFoodSelected(item);
            }
        });

        calories = findViewById(R.id.calories_total);
        prots = findViewById(R.id.prots_total);
        carbs = findViewById(R.id.carbs_total);
        fats = findViewById(R.id.fats_total);

        currentDayText = findViewById(R.id.diary_food_currentday);
        listView = findViewById(R.id.diary_list);
        listView.setAdapter(listAdapter);

        addFoodBtn = findViewById(R.id.diary_food_add_food_btn);
        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick(): Navigate to add Food Screen");
                presenter.onAddFoodButton();
            }
        });

        // do the setup
        DiaryFoodScreen.configure(this);
        presenter.fetchDiaryFood();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void displayData(final DiaryFoodViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.setItems(viewModel.diaryFood);
                Log.d(TAG, "displayData(): List size: "+ viewModel.diaryFood.size());
                SimpleDateFormat format1 = new SimpleDateFormat("EE yyyy-MM-dd");
                Log.d(TAG, viewModel.currentDay.toString() + " - " + new Date().toString());
                if(viewModel.currentDay.getDay() == new Date().getDay()) {
                    Log.d(TAG, viewModel.currentDay.toString() + " - " + new Date().toString());
                    currentDayText.setText("Hoy");
                } else {
                    currentDayText.setText(format1.format(viewModel.currentDay));
                }
                double totalCalories = 0;
                double totalProts = 0;
                double totalCarbs = 0;
                double totalFats = 0;
                for(DiaryFood food : viewModel.diaryFood) {
                    totalCalories += (food.calories * food.quantity)/100;
                    totalProts += (food.proteins * food.quantity)/100;
                    totalCarbs += (food.carbs * food.quantity)/100;
                    totalFats += (food.fats * food.quantity)/100;
                }
                calories.setText(new DecimalFormat("#.##").format(totalCalories) + " kcal");
                prots.setText(new DecimalFormat("#.##").format(totalProts) + " gr.");
                carbs.setText(new DecimalFormat("#.##").format(totalCarbs) + " gr.");
                fats.setText(new DecimalFormat("#.##").format(totalFats) + " gr.");
            }
        });
    }

    @Override
    public void injectPresenter(DiaryFoodContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diary_menu, toolbar.getMenu());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nextday:
                Log.d(TAG, "onNextDayOption()");
                presenter.onNextDayButton();
                return true;
            case R.id.action_pastday:
                Log.d(TAG, "onPastDayOption()");
                presenter.onPreviousDayButton();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
