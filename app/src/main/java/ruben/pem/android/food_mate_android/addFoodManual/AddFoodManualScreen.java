package ruben.pem.android.food_mate_android.addFoodManual;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class AddFoodManualScreen {

    public static void configure(AddFoodManualContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        AddFoodManualState state = mediator.getAddFoodManualState();
        RepositoryContract repository = Repository.getInstance(context.get());

        AddFoodManualContract.Router router = new AddFoodManualRouter(mediator);
        AddFoodManualContract.Presenter presenter = new AddFoodManualPresenter(state);
        AddFoodManualContract.Model model = new AddFoodManualModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
