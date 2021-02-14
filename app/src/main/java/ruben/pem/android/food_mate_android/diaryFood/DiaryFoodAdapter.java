package ruben.pem.android.food_mate_android.diaryFood;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ruben.pem.android.food_mate_android.R;
import ruben.pem.android.food_mate_android.data.DiaryFood;

public class DiaryFoodAdapter extends RecyclerView.Adapter<DiaryFoodAdapter.ViewHolder> {

    private static final String TAG = DiaryFoodAdapter.class.getSimpleName();

    private List<DiaryFood> diaryFoodList;
    private View.OnClickListener listener;

    public DiaryFoodAdapter(View.OnClickListener listener) {
        diaryFoodList = new ArrayList<>();
        this.listener = listener;
    }

    public void addItem(DiaryFood item){
        diaryFoodList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<DiaryFood> items){
        diaryFoodList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<DiaryFood> items){
        diaryFoodList = items;
        Log.d(TAG, "setItems(): List size: "+ items.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diaryfood_item_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(diaryFoodList.get(position));
        holder.itemView.setOnClickListener(listener);

        double caloriesTotal = (diaryFoodList.get(position).calories * diaryFoodList.get(position).quantity)/100;

        holder.foodName.setText(diaryFoodList.get(position).name);
        holder.foodCalories.setText(String.valueOf(caloriesTotal) + " kcal");
    }

    @Override
    public int getItemCount() {
        return diaryFoodList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView foodName;
        final TextView foodCalories;

        ViewHolder(View view) {
            super(view);
            foodName = view.findViewById(R.id.food_name);
            foodCalories = view.findViewById(R.id.food_calories);
        }
    }
}
