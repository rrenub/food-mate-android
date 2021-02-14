package ruben.pem.android.food_mate_android.ScannerBarcode;

import java.lang.ref.WeakReference;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.app.ScannerToAddFoodState;

public interface BCScannerContract {

    interface View {
        void injectPresenter(Presenter presenter);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void onBarcodeDetected(String barcode);
    }

    interface Model {
        void getFoodFromBC(String barcode, RepositoryContract.GetFoodFromBarcodeAPI getFoodFromBarcodeAPI);
    }

    interface Router {
        void navigateToNextScreen();

        BCScannerState getDataFromPreviousScreen();

        void passDataToAddScreen(ScannerToAddFoodState stateToAdd);
    }
}
