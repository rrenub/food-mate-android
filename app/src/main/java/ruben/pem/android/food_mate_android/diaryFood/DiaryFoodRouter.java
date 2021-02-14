package ruben.pem.android.food_mate_android.diaryFood;

import android.content.Intent;
import android.content.Context;

import ruben.pem.android.food_mate_android.DiaryFoodDetail.DiaryFoodDetailActivity;
import ruben.pem.android.food_mate_android.addFoodList.AddFoodListActivity;
import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.DiaryListToDetailState;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodRouter implements DiaryFoodContract.Router {

    public static String TAG = DiaryFoodRouter.class.getSimpleName();

    private AppMediator mediator;

    public DiaryFoodRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToAddFoodScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddFoodListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passDataToDetailScreen(DiaryFood item) {
        DiaryListToDetailState state = new DiaryListToDetailState();
        state.diaryFood = item;
        mediator.setDiaryListToDetailState(state);
    }

    @Override
    public void navigateToDetailScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, DiaryFoodDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
