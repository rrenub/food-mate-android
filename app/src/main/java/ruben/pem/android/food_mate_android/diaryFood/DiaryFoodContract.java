package ruben.pem.android.food_mate_android.diaryFood;

import java.lang.ref.WeakReference;
import java.util.Date;

import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public interface DiaryFoodContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(DiaryFoodViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchDiaryFood();

        void onAddFoodButton();

        void onNextDayButton();

        void onPreviousDayButton();

        void onDiaryFoodSelected(DiaryFood item);
    }

    interface Model {
        void fetchDiaryFood(Date date, RepositoryContract.GetDiaryFoodCallback getDiaryFoodCallback);

        void updateDate(Date currentDay);

        Date getCurrentDate();
    }

    interface Router {
        void navigateToAddFoodScreen();

        void passDataToDetailScreen(DiaryFood item);

        void navigateToDetailScreen();
    }
}
