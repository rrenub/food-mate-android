package ruben.pem.android.food_mate_android.diaryFood;

import android.util.Log;

import java.util.Date;

import ruben.pem.android.food_mate_android.app.RepositoryContract;

public class DiaryFoodModel implements DiaryFoodContract.Model {

    public static String TAG = DiaryFoodModel.class.getSimpleName();

    private RepositoryContract repository;

    public DiaryFoodModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchDiaryFood(Date date, RepositoryContract.GetDiaryFoodCallback getDiaryFoodCallback) {
        repository.getDiaryFood(date, getDiaryFoodCallback);
    }

    @Override
    public void updateDate(Date currentDay) {
        repository.updateDate(currentDay);
    }

    @Override
    public Date getCurrentDate() {
        return repository.getCurrentDate();
    }
}
