package ruben.pem.android.food_mate_android.DiaryFoodDetail;

import android.content.Intent;
import android.content.Context;

import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.DiaryListToDetailState;
import ruben.pem.android.food_mate_android.diaryFood.DiaryFoodActivity;

public class DiaryFoodDetailRouter implements DiaryFoodDetailContract.Router {

    public static String TAG = DiaryFoodDetailRouter.class.getSimpleName();

    private AppMediator mediator;

    public DiaryFoodDetailRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToHomeScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, DiaryFoodActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public DiaryListToDetailState getDataFromList() {
        return  mediator.getDiaryListToDetailState();
    }
}
