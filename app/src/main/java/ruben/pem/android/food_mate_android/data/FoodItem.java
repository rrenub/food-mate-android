package ruben.pem.android.food_mate_android.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "foodItem")
public class FoodItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "calories")
    public double calories;

    @ColumnInfo(name = "proteins")
    public double proteins;

    @ColumnInfo(name = "carbs")
    public double carbs;

    @ColumnInfo(name = "fats")
    public double fats;

    public FoodItem(String name, double calories, double proteins, double carbs, double fats) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodItem model = (FoodItem) o;

        if (id != model.id) return false;
        return name != null ? name.equals(model.name) : model.name == null;

    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", carbs=" + carbs +
                ", fats=" + fats +
                '}';
    }
}