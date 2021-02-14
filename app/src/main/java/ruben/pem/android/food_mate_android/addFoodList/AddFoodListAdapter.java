package ruben.pem.android.food_mate_android.addFoodList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ruben.pem.android.food_mate_android.R;
import ruben.pem.android.food_mate_android.data.DiaryFood;
import ruben.pem.android.food_mate_android.data.FoodItem;

public class AddFoodListAdapter extends RecyclerView.Adapter<AddFoodListAdapter.ViewHolder> {
    private final static String TAG = AddFoodListAdapter.class.getSimpleName();

    private List<FoodItem> originalFoodList;
    private List<FoodItem> foodList;
    private View.OnClickListener listener;

    public AddFoodListAdapter(View.OnClickListener listener) {
        originalFoodList = new ArrayList<>();
        foodList = new ArrayList<>();
        this.listener = listener;
    }

    public void addItem(FoodItem item){
        foodList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<FoodItem> items){
        foodList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<FoodItem> items){
        foodList = items;
        originalFoodList.clear();
        originalFoodList.addAll(foodList);
        Log.d(TAG, "setItems(): List size FOOD: "+ items.size());
        notifyDataSetChanged();
    }

    public void filter(final String searchQuery) {
        if(searchQuery.length() == 0) {
            foodList.clear();
            foodList.addAll(originalFoodList);
        } else {
            List<FoodItem> list = originalFoodList.stream()
                    .filter(i -> i.name.toLowerCase().contains(searchQuery))
                    .collect(Collectors.toList());
            foodList.clear();
            foodList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_food_item_content, parent, false);
        return new AddFoodListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(foodList.get(position));
        holder.itemView.setOnClickListener(listener);

        holder.foodName.setText(foodList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView foodName;

        ViewHolder(View view) {
            super(view);
            foodName = view.findViewById(R.id.add_food_name);
        }
    }

    public void removeItem(int position) {
        foodList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(FoodItem item, int position) {
        foodList.add(position, item);
        notifyItemInserted(position);
    }

    public List<FoodItem> getData() {
        return foodList;
    }
}


