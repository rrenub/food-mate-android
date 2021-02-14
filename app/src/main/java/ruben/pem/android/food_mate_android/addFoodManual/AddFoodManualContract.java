package ruben.pem.android.food_mate_android.addFoodManual;

import java.lang.ref.WeakReference;
import java.util.Date;

import ruben.pem.android.food_mate_android.ScannerBarcode.BCScannerState;
import ruben.pem.android.food_mate_android.app.FoodListToAddState;
import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.app.ScannerToAddFoodState;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public interface AddFoodManualContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(AddFoodManualViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void onAddButton(String[] foodInfo);
    }

    interface Model {
        void addFood(DiaryFood diaryFood, RepositoryContract.InsertNewFoodItem callback);

        Date getCurrentDate();
    }

    interface Router {
        void navigateToNextScreen();

        ScannerToAddFoodState getDataFromBarScanner();

        FoodListToAddState getDataFromFoodList();
    }
}
