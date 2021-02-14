package ruben.pem.android.food_mate_android.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "diaryfood")
public class DiaryFood {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "quantity")
    public double quantity;

    @ColumnInfo(name = "calories")
    public double calories;

    @ColumnInfo(name = "proteins")
    public double proteins;

    @ColumnInfo(name = "carbs")
    public double carbs;

    @ColumnInfo(name = "fats")
    public double fats;

    @ColumnInfo(name = "date")
    public Date date;

    public DiaryFood(String name, double quantity, double calories, Date date, double proteins, double carbs, double fats) {
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
        this.date = date;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "DiaryFood{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", date=" + date +
                '}';
    }
}
