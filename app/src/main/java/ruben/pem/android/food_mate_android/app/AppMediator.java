package ruben.pem.android.food_mate_android.app;

import android.app.Application;

import ruben.pem.android.food_mate_android.DiaryFoodDetail.DiaryFoodDetailState;
import ruben.pem.android.food_mate_android.ScannerBarcode.BCScannerState;
import ruben.pem.android.food_mate_android.addFoodList.AddFoodListState;
import ruben.pem.android.food_mate_android.addFoodManual.AddFoodManualState;
import ruben.pem.android.food_mate_android.diaryFood.DiaryFoodState;


public class AppMediator extends Application {

    public AppMediator() {
        super();
    }

    private FoodListToAddState foodListToAddState = null;

    private ScannerToAddFoodState scannerToAddFoodState = null;

    private DiaryListToDetailState diaryListToDetailState = null;

    private DiaryFoodDetailState diaryFoodDetailState = new DiaryFoodDetailState();

    private DiaryFoodState diaryFoodState = new DiaryFoodState();

    private AddFoodListState addFoodListState = new AddFoodListState();

    private AddFoodManualState addFoodManualState = new AddFoodManualState();

    private BCScannerState bcScannerState = new BCScannerState();

    public BCScannerState getBcScannerState() {
        return bcScannerState;
    }

    public void setBcScannerState(BCScannerState bcScannerState) {
        this.bcScannerState = bcScannerState;
    }

    public AddFoodListState getAddFoodListState() {
        return addFoodListState;
    }

    public void setAddFoodListState(AddFoodListState addFoodListState) {
        this.addFoodListState = addFoodListState;
    }

    public DiaryFoodState getDiaryFoodState() {
        return diaryFoodState;
    }

    public void setDiaryFoodState(DiaryFoodState diaryFoodState) {
        this.diaryFoodState = diaryFoodState;
    }

    public AddFoodManualState getAddFoodManualState() {
        return addFoodManualState;
    }

    public void setAddFoodManualState(AddFoodManualState addFoodManualState) {
        this.addFoodManualState = addFoodManualState;
    }

    public ScannerToAddFoodState getScannerToAddFoodState() {
        return scannerToAddFoodState;
    }

    public void setScannerToAddFoodState(ScannerToAddFoodState scannerToAddFoodState) {
        this.scannerToAddFoodState = scannerToAddFoodState;
    }

    public DiaryFoodDetailState getDiaryFoodDetailState() {
        return diaryFoodDetailState;
    }

    public void setDiaryFoodDetailState(DiaryFoodDetailState diaryFoodDetailState) {
        this.diaryFoodDetailState = diaryFoodDetailState;
    }

    public DiaryListToDetailState getDiaryListToDetailState() {
        DiaryListToDetailState savedState = diaryListToDetailState;
        diaryListToDetailState = null;
        return savedState;
    }

    public void setDiaryListToDetailState(DiaryListToDetailState diaryListToDetailState) {
        this.diaryListToDetailState = diaryListToDetailState;
    }

    public FoodListToAddState getFoodListToAddState() {
        FoodListToAddState savedState = foodListToAddState;
        foodListToAddState = null;
        return savedState;
    }

    public void setFoodListToAddState(FoodListToAddState foodListToAddState) {
        this.foodListToAddState = foodListToAddState;
    }
}
