package ruben.pem.android.food_mate_android.addFoodManual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

import ruben.pem.android.food_mate_android.R;

public class AddFoodManualActivity
        extends AppCompatActivity implements AddFoodManualContract.View {

    public static String TAG = AddFoodManualActivity.class.getSimpleName();

    private AddFoodManualContract.Presenter presenter;

    private Toolbar toolbar;
    private TextInputEditText foodNameInput, caloriesInput, quantityInput, proteinInput, carbsInput, fatsInput;
    private MaterialButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_manual);
        initLayout();
        // do the setup
        AddFoodManualScreen.configure(this);
    }

    private void initLayout() {
        toolbar = findViewById(R.id.toolbar_add_manual);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.add_food_list_name);

        foodNameInput = findViewById(R.id.foodname_input);
        caloriesInput = findViewById(R.id.calories_input);
        quantityInput = findViewById(R.id.quantity_input);
        proteinInput = findViewById(R.id.prots_input);
        carbsInput = findViewById(R.id.carbs_input);
        fatsInput = findViewById(R.id.fats_input);

        addButton = findViewById(R.id.food_add_food_manual_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.fetchData();
    }

    @Override
    public void displayData(AddFoodManualViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
        foodNameInput.setText(viewModel.foodName);
        caloriesInput.setText(viewModel.calories);
        carbsInput.setText(viewModel.carbs);
        fatsInput.setText(viewModel.fats);
        proteinInput.setText(viewModel.proteins);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_manual_menu, toolbar.getMenu());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                onAddButtonClicked();
                Log.d(TAG, "onAddManuallyCHECK()");
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void onAddButtonClicked() {
        if(foodNameInput.getText().toString().isEmpty()
                || quantityInput.getText().toString().isEmpty()
                || caloriesInput.getText().toString().isEmpty()
                || proteinInput.getText().toString().isEmpty()
                || carbsInput.getText().toString().isEmpty()
                || fatsInput.getText().toString().isEmpty() ) {
            Toast.makeText(this, R.string.warning_form, Toast.LENGTH_LONG).show();
            return;
        }

        String foodInfo[] = new String[] {
                foodNameInput.getText().toString(),
                quantityInput.getText().toString(),
                caloriesInput.getText().toString(),
                proteinInput.getText().toString(),
                carbsInput.getText().toString(),
                fatsInput.getText().toString()
        };

        Log.d(TAG, Arrays.toString(foodInfo));
        presenter.onAddButton(foodInfo);
    }

    @Override
    public void injectPresenter(AddFoodManualContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
