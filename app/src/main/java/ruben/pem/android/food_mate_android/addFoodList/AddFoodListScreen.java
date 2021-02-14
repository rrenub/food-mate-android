package ruben.pem.android.food_mate_android.addFoodList;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class AddFoodListScreen {

    public static void configure(AddFoodListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        AddFoodListState state = mediator.getAddFoodListState();
        RepositoryContract repository = Repository.getInstance(context.get());


        AddFoodListContract.Router router = new AddFoodListRouter(mediator);
        AddFoodListContract.Presenter presenter = new AddFoodListPresenter(state);
        AddFoodListContract.Model model = new AddFoodListModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
