package ruben.pem.android.food_mate_android.addFoodList;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.FoodItem;

public class AddFoodListModel implements AddFoodListContract.Model {

    public static String TAG = AddFoodListModel.class.getSimpleName();

    private RepositoryContract repository;

    public AddFoodListModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchFoodList(final RepositoryContract.GetFoodListCallback getFoodListCallback) {
        repository.loadFoodList(true, new RepositoryContract.FetchFoodListJSONCallback() {
            @Override
            public void onJSONDataFetched(boolean error) {
                if(!error) {
                    repository.getFoodList(getFoodListCallback);
                }
            }
        });
    }

    @Override
    public void deleteFood(FoodItem item, RepositoryContract.DeleteFoodItem deleteFoodItem) {
        repository.deleteFoodItem(item, deleteFoodItem);
    }
}
