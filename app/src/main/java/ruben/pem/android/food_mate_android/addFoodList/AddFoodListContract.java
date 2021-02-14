package ruben.pem.android.food_mate_android.addFoodList;

import java.lang.ref.WeakReference;
import java.util.List;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.FoodItem;

public interface AddFoodListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(AddFoodListViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchFoodListData();

        void onAddManually();

        void onAddScanner();

        void onFoodSelected(FoodItem foodItem);

        void onItemDeleted(FoodItem item);
    }

    interface Model {
        void fetchFoodList(RepositoryContract.GetFoodListCallback getFoodListCallback);

        void deleteFood(FoodItem item, RepositoryContract.DeleteFoodItem deleteFoodItem);
    }

    interface Router {

        void navigateToAddManually();

        void navigateToScannerBarcode();

        void passDataFromListToAdd(FoodItem foodItem);
    }
}
