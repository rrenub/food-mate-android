package ruben.pem.android.food_mate_android.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import ruben.pem.android.food_mate_android.data.DiaryFood;

@Dao
public interface DiaryFoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDiaryFood(DiaryFood diaryFood);

    @Delete
    void deleteDiaryFood(DiaryFood diaryFood);

    @Query("SELECT * FROM diaryfood")
    List<DiaryFood> loadDiaryFood();

    @Query("SELECT * FROM diaryfood WHERE date BETWEEN :from AND :to")
    List<DiaryFood> findDiaryFoodBetweenDates(Date from, Date to);
}
