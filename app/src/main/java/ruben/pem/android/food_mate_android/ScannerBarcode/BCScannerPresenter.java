package ruben.pem.android.food_mate_android.ScannerBarcode;

import android.util.Log;

import java.lang.ref.WeakReference;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.app.ScannerToAddFoodState;

public class BCScannerPresenter implements BCScannerContract.Presenter {

    public static String TAG = BCScannerPresenter.class.getSimpleName();

    private WeakReference<BCScannerContract.View> view;
    private BCScannerState state;
    private BCScannerContract.Model model;
    private BCScannerContract.Router router;

    public BCScannerPresenter(BCScannerState state) {
        this.state = state;
    }

    @Override
    public void onBarcodeDetected(String barcode) {
        Log.d(TAG, barcode);

        model.getFoodFromBC(barcode, new RepositoryContract.GetFoodFromBarcodeAPI() {
            @Override
            public void foodFoundAPI(String name, String calories, String prots, String carbs, String fats) {
                ScannerToAddFoodState stateToAdd = new ScannerToAddFoodState();
                stateToAdd.nome = name;
                stateToAdd.calories = calories;
                stateToAdd.carbs = carbs;
                stateToAdd.proteins = prots;
                stateToAdd.fats = fats;
                router.passDataToAddScreen(stateToAdd);
                router.navigateToNextScreen();
            }
        });
    }

    @Override
    public void injectView(WeakReference<BCScannerContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(BCScannerContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(BCScannerContract.Router router) {
        this.router = router;
    }
}
