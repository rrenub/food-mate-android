package ruben.pem.android.food_mate_android.ScannerBarcode;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import ruben.pem.android.food_mate_android.app.AppMediator;
import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class BCScannerScreen {

    public static void configure(BCScannerContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        BCScannerState state = mediator.getBcScannerState();
        RepositoryContract repository = Repository.getInstance(context.get());

        BCScannerContract.Router router = new BCScannerRouter(mediator);
        BCScannerContract.Presenter presenter = new BCScannerPresenter(state);
        BCScannerContract.Model model = new BCScannerModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
