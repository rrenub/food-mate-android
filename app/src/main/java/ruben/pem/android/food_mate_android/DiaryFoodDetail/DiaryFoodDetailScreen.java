package ruben.pem.android.food_mate_android.DiaryFoodDetail;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class DiaryFoodDetailScreen {

    public static void configure(DiaryFoodDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        DiaryFoodDetailState state = mediator.getDiaryFoodDetailState();
        RepositoryContract repository = Repository.getInstance(context.get());

        DiaryFoodDetailContract.Router router = new DiaryFoodDetailRouter(mediator);
        DiaryFoodDetailContract.Presenter presenter = new DiaryFoodDetailPresenter(state);
        DiaryFoodDetailContract.Model model = new DiaryFoodDetailModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
