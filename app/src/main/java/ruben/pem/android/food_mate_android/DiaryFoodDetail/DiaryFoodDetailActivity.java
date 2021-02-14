package ruben.pem.android.food_mate_android.DiaryFoodDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import ruben.pem.android.food_mate_android.R;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodDetailActivity
        extends AppCompatActivity implements DiaryFoodDetailContract.View {

    public static String TAG = DiaryFoodDetailActivity.class.getSimpleName();

    private Toolbar toolbar;
    private MaterialButton deleteButton;


    private DiaryFoodDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_food_detail);

        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Editar alimento");

        deleteButton = findViewById(R.id.detail_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDeleteButton();
            }
        });


        // do the setup
        DiaryFoodDetailScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.fetchData();
    }

    @Override
    public void displayData(DiaryFoodDetailViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
        String proteins = (viewModel.foodItem.proteins) + " gr.";
        String carbs = (viewModel.foodItem.carbs) + " gr.";
        String fats = (viewModel.foodItem.fats) + " gr.";

        String quantity = (viewModel.foodItem.quantity) + " gr.";
        String foodName = String.valueOf(viewModel.foodItem.name);
        String kcalIngested = getResources().getString(R.string.kcal_ingested);
        String totalCalories = (viewModel.foodItem.calories * viewModel.foodItem.quantity)/100 + kcalIngested;

        ((TextView) findViewById(R.id.detail_proteins)).setText(proteins);
        ((TextView) findViewById(R.id.detail_carbs)).setText(carbs);
        ((TextView) findViewById(R.id.detail_fats)).setText(fats);
        ((TextView) findViewById(R.id.detail_food_name)).setText(foodName);
        ((TextView) findViewById(R.id.detail_total_calories)).setText(totalCalories);
        ((TextView) findViewById(R.id.detail_quantity)).setText(quantity);

    }

    @Override
    public void injectPresenter(DiaryFoodDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
