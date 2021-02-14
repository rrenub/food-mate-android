package ruben.pem.android.food_mate_android.diaryFood;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodPresenter implements DiaryFoodContract.Presenter {

    public static String TAG = DiaryFoodPresenter.class.getSimpleName();

    private WeakReference<DiaryFoodContract.View> view;
    private DiaryFoodState state;
    private DiaryFoodContract.Model model;
    private DiaryFoodContract.Router router;

    public DiaryFoodPresenter(DiaryFoodState state) {
        this.state = state;
    }

    @Override
    public void fetchDiaryFood() {
        state.currentDay = model.getCurrentDate();
        model.fetchDiaryFood(state.currentDay, new RepositoryContract.GetDiaryFoodCallback() {
            @Override
            public void setDiaryFoodList(List<DiaryFood> list) {
                state.diaryFood = list;
                view.get().displayData(state);
            }
        });
    }

    @Override
    public void onAddFoodButton() {
        router.navigateToAddFoodScreen();
    }

    @Override
    public void onNextDayButton() {
        state.currentDay = addDaysToDate(state.currentDay, 1);
        model.updateDate(state.currentDay);
        fetchDiaryFood();
    }

    @Override
    public void onPreviousDayButton() {
        state.currentDay = addDaysToDate(state.currentDay, -1);
        model.updateDate(state.currentDay);
        fetchDiaryFood();
    }

    private Date addDaysToDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    @Override
    public void onDiaryFoodSelected(DiaryFood item) {
        router.passDataToDetailScreen(item);
        router.navigateToDetailScreen();
    }

    @Override
    public void injectView(WeakReference<DiaryFoodContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(DiaryFoodContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(DiaryFoodContract.Router router) {
        this.router = router;
    }
}
