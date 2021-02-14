package ruben.pem.android.food_mate_android.addFoodManual;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Date;

import ruben.pem.android.food_mate_android.app.FoodListToAddState;
import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.app.ScannerToAddFoodState;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class AddFoodManualPresenter implements AddFoodManualContract.Presenter {

    public static String TAG = AddFoodManualPresenter.class.getSimpleName();

    private WeakReference<AddFoodManualContract.View> view;
    private AddFoodManualState state;
    private AddFoodManualContract.Model model;
    private AddFoodManualContract.Router router;

    public AddFoodManualPresenter(AddFoodManualState state) {
        this.state = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        if (state == null) {
            state = new AddFoodManualState();
        }

        // use passed state if is necessary
        ScannerToAddFoodState savedState = router.getDataFromBarScanner();
        if (savedState != null) {

            state.foodName = savedState.nome;
            state.calories = savedState.calories;
            state.proteins = savedState.proteins;
            state.carbs = savedState.carbs;
            state.fats = savedState.fats;

            // update the view
            view.get().displayData(state);
            return;
        }

        // use passed state if is necessary
        FoodListToAddState itemState = router.getDataFromFoodList();
        if (itemState != null) {

            state.foodName = itemState.foodItem.name;
            state.calories = String.valueOf(itemState.foodItem.calories);
            state.proteins = String.valueOf(itemState.foodItem.proteins);
            state.carbs = String.valueOf(itemState.foodItem.carbs);
            state.fats = String.valueOf(itemState.foodItem.fats);

            // update the view
            view.get().displayData(state);
            return;
        }

        // set view state
        state.foodName = "";
        state.calories = "";
        state.proteins = "";
        state.fats = "";
        state.carbs = "";

        // update the view
        view.get().displayData(state);
    }

    @Override
    public void onAddButton(String[] foodInfo) {
        Log.d(TAG, Arrays.toString(foodInfo));
        Date date = model.getCurrentDate();
        DiaryFood diaryFood = new DiaryFood(
                foodInfo[0],
                Double.valueOf(foodInfo[1]),
                Double.valueOf(foodInfo[2]),
                date,
                Double.valueOf(foodInfo[3]),
                Double.valueOf(foodInfo[4]),
                Double.valueOf(foodInfo[5]));
        Log.d(TAG, diaryFood.toString());
                model.addFood(diaryFood, new RepositoryContract.InsertNewFoodItem() {
            @Override
            public void foodInserted() {
                router.navigateToNextScreen();
            }
        });
    }

    @Override
    public void injectView(WeakReference<AddFoodManualContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(AddFoodManualContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(AddFoodManualContract.Router router) {
        this.router = router;
    }
}
