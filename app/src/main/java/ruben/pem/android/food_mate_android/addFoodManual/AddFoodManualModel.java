package ruben.pem.android.food_mate_android.addFoodManual;

import java.util.Date;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.DiaryFood;
import ruben.pem.android.food_mate_android.data.FoodItem;

public class AddFoodManualModel implements AddFoodManualContract.Model {

    public static String TAG = AddFoodManualModel.class.getSimpleName();

    private RepositoryContract repository;

    public AddFoodManualModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void addFood(DiaryFood diaryFood, RepositoryContract.InsertNewFoodItem callback) {
        repository.addFood(diaryFood, callback);
    }

    @Override
    public Date getCurrentDate() {
        return repository.getCurrentDate();
    }
}
