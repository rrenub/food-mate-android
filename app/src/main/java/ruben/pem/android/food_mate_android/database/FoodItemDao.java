package ruben.pem.android.food_mate_android.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ruben.pem.android.food_mate_android.data.FoodItem;

@Dao
public interface FoodItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFoodItem(FoodItem foodItem);

    @Delete
    void deleteFoodItem(FoodItem foodItem);

    @Query("SELECT * FROM foodItem")
    List<FoodItem> loadDiaryFood();

    @Query("SELECT * FROM foodItem WHERE fooditem.name = :name")
    List<FoodItem> checkFoodItem(String name);

}
