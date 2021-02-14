package ruben.pem.android.food_mate_android.DiaryFoodDetail;

import java.lang.ref.WeakReference;

import ruben.pem.android.food_mate_android.app.DiaryListToDetailState;
import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public interface DiaryFoodDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(DiaryFoodDetailViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void onDeleteButton();
    }

    interface Model {
        void deleteDiaryFood(DiaryFood tag, RepositoryContract.DeleteDiaryFoodCallback deleteDiaryFoodCallback);
    }

    interface Router {

        DiaryListToDetailState getDataFromList();

        void navigateToHomeScreen();
    }
}
