package ruben.pem.android.food_mate_android.DiaryFoodDetail;

import java.lang.ref.WeakReference;

import ruben.pem.android.food_mate_android.app.DiaryListToDetailState;
import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodDetailPresenter implements DiaryFoodDetailContract.Presenter {

    public static String TAG = DiaryFoodDetailPresenter.class.getSimpleName();

    private WeakReference<DiaryFoodDetailContract.View> view;
    private DiaryFoodDetailState state;
    private DiaryFoodDetailContract.Model model;
    private DiaryFoodDetailContract.Router router;

    public DiaryFoodDetailPresenter(DiaryFoodDetailState state) {
        this.state = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // initialize the state if is necessary
        if (state == null) {
            state = new DiaryFoodDetailState();
        }

        // use passed state if is necessary
        DiaryListToDetailState savedState = router.getDataFromList();
        if (savedState != null) {

            state.foodItem = savedState.diaryFood;

            // update the view
            view.get().displayData(state);
            return;
        }
        view.get().displayData(state);
    }

    @Override
    public void onDeleteButton() {
        model.deleteDiaryFood(state.foodItem, new RepositoryContract.DeleteDiaryFoodCallback() {
            @Override
            public void deletedDiaryFood() {
                router.navigateToHomeScreen();
            }
        });
    }

    @Override
    public void injectView(WeakReference<DiaryFoodDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(DiaryFoodDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(DiaryFoodDetailContract.Router router) {
        this.router = router;
    }
}
