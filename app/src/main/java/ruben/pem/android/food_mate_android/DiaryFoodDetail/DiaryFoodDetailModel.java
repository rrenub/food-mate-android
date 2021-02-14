package ruben.pem.android.food_mate_android.DiaryFoodDetail;

import ruben.pem.android.food_mate_android.app.Repository;
import ruben.pem.android.food_mate_android.app.RepositoryContract;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodDetailModel implements DiaryFoodDetailContract.Model {

    public static String TAG = DiaryFoodDetailModel.class.getSimpleName();

    private RepositoryContract repository;

    public DiaryFoodDetailModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void deleteDiaryFood(DiaryFood tag, RepositoryContract.DeleteDiaryFoodCallback deleteDiaryFoodCallback) {
        repository.deleteDiaryFood(tag, deleteDiaryFoodCallback);
    }
}
