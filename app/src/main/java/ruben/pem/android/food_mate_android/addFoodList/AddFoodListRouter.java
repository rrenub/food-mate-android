package ruben.pem.android.food_mate_android.addFoodList;

import android.content.Intent;
import android.content.Context;

import ruben.pem.android.food_mate_android.ScannerBarcode.BCScannerActivity;
import ruben.pem.android.food_mate_android.addFoodManual.AddFoodManualActivity;
import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.FoodListToAddState;
import ruben.pem.android.food_mate_android.data.FoodItem;

public class AddFoodListRouter implements AddFoodListContract.Router {

    public static String TAG = AddFoodListRouter.class.getSimpleName();

    private AppMediator mediator;

    public AddFoodListRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToAddManually() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddFoodManualActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void navigateToScannerBarcode() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, BCScannerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passDataFromListToAdd(FoodItem foodItem) {
        FoodListToAddState newState = new FoodListToAddState();
        newState.foodItem = foodItem;
        mediator.setFoodListToAddState(newState);
    }
}
