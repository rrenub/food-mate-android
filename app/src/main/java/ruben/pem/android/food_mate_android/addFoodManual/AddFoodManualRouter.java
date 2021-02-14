package ruben.pem.android.food_mate_android.addFoodManual;

import android.content.Intent;
import android.content.Context;
import android.util.Log;

import ruben.pem.android.food_mate_android.ScannerBarcode.BCScannerState;
import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.FoodListToAddState;
import ruben.pem.android.food_mate_android.app.ScannerToAddFoodState;
import ruben.pem.android.food_mate_android.diaryFood.DiaryFoodActivity;

public class AddFoodManualRouter implements AddFoodManualContract.Router {

    public static String TAG = AddFoodManualRouter.class.getSimpleName();

    private AppMediator mediator;

    public AddFoodManualRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, DiaryFoodActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public FoodListToAddState getDataFromFoodList() {
        return mediator.getFoodListToAddState();
    }

    @Override
    public ScannerToAddFoodState getDataFromBarScanner() {
        ScannerToAddFoodState passedState = mediator.getScannerToAddFoodState();
        mediator.setScannerToAddFoodState(null);
        return passedState;
    }
}
