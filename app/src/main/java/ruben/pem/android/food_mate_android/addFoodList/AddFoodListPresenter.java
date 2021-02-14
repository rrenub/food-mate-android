package ruben.pem.android.food_mate_android.addFoodList;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.FoodItem;

public class AddFoodListPresenter implements AddFoodListContract.Presenter {

    public static String TAG = AddFoodListPresenter.class.getSimpleName();

    private WeakReference<AddFoodListContract.View> view;
    private AddFoodListState state;
    private AddFoodListContract.Model model;
    private AddFoodListContract.Router router;

    public AddFoodListPresenter(AddFoodListState state) {
        this.state = state;
    }

    @Override
    public void fetchFoodListData() {
        model.fetchFoodList(new RepositoryContract.GetFoodListCallback() {

            @Override
            public void setFoodList(List<FoodItem> list) {
                state.foodList = list;
                view.get().displayData(state);
            }
        });
    }

    @Override
    public void onFoodSelected(FoodItem foodItem) {
        Log.d(TAG, foodItem.toString());
        router.passDataFromListToAdd(foodItem);
        router.navigateToAddManually();
    }

    @Override
    public void onItemDeleted(FoodItem item) {
        model.deleteFood(item, new RepositoryContract.DeleteFoodItem() {
            @Override
            public void onDeletedFood(FoodItem item) {
                state.foodList.remove(item);
                view.get().displayData(state);
            }
        });
    }

    @Override
    public void onAddManually() {
        router.navigateToAddManually();
    }

    @Override
    public void onAddScanner() {
        router.navigateToScannerBarcode();
    }

    @Override
    public void injectView(WeakReference<AddFoodListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(AddFoodListContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(AddFoodListContract.Router router) {
        this.router = router;
    }
}
