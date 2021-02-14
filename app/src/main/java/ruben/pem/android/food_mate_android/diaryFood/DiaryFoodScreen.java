package ruben.pem.android.food_mate_android.diaryFood;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class DiaryFoodScreen {

    public static void configure(DiaryFoodContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        DiaryFoodState state = mediator.getDiaryFoodState();
        RepositoryContract repository = Repository.getInstance(context.get());

        DiaryFoodContract.Router router = new DiaryFoodRouter(mediator);
        DiaryFoodContract.Presenter presenter = new DiaryFoodPresenter(state);
        DiaryFoodContract.Model model = new DiaryFoodModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
