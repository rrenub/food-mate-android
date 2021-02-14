package ruben.pem.android.food_mate_android.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ruben.pem.android.food_mate_android.data.DiaryFood;
import ruben.pem.android.food_mate_android.data.FoodItem;

@Database(entities = {DiaryFood.class, FoodItem.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract DiaryFoodDao diaryFoodDao();
    public abstract FoodItemDao foodItemDao();
}
