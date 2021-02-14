package ruben.pem.android.food_mate_android.ScannerBarcode;

import android.util.Log;

import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class BCScannerModel implements BCScannerContract.Model {

    public static String TAG = BCScannerModel.class.getSimpleName();

    private RepositoryContract repository;

    public BCScannerModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void getFoodFromBC(String barcode, RepositoryContract.GetFoodFromBarcodeAPI getFoodFromBarcodeAPI) {
        repository.getFoodFromBarcode(barcode, getFoodFromBarcodeAPI);
    }
}
