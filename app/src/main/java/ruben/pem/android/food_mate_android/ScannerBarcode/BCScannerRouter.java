package ruben.pem.android.food_mate_android.ScannerBarcode;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import ruben.pem.android.food_mate_android.addFoodManual.AddFoodManualActivity;
import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.ScannerToAddFoodState;
import ruben.pem.android.food_mate_android.diaryFood.DiaryFoodActivity;

public class BCScannerRouter implements BCScannerContract.Router {

    public static String TAG = BCScannerRouter.class.getSimpleName();

    private AppMediator mediator;

    public BCScannerRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddFoodManualActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public BCScannerState getDataFromPreviousScreen() {
        BCScannerState state = mediator.getBcScannerState();
        return state;
    }

    @Override
    public void passDataToAddScreen(ScannerToAddFoodState stateToAdd) {
        mediator.setScannerToAddFoodState(stateToAdd);
    }
}
