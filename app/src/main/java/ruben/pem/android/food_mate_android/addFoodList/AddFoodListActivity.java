package ruben.pem.android.food_mate_android.addFoodList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.Comparator;

import ruben.pem.android.food_mate_android.R;
import ruben.pem.android.food_mate_android.data.FoodItem;

public class AddFoodListActivity
        extends AppCompatActivity implements AddFoodListContract.View, SearchView.OnQueryTextListener {
    public static String TAG = AddFoodListActivity.class.getSimpleName();

    private AddFoodListContract.Presenter presenter;

    private SearchView searchView;
    private AddFoodListAdapter listAdapter;
    private Toolbar toolbar;
    private RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_list);

        toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.add_food_list_name);

        listAdapter = new AddFoodListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DiaryFood item = (DiaryFood) view.getTag();
                Log.d(TAG, "onClick(): ");
                presenter.onFoodSelected((FoodItem) view.getTag());
            }
        });
        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.add_food_list);
        listView.setAdapter(listAdapter);

        enableSwipeToDeleteAndUndo();

        // do the setup
        AddFoodListScreen.configure(this);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                FoodItem item = listAdapter.getData().get(position);
                Log.d(TAG, "Se va a borrar el elemento " + item.name);
                presenter.onItemDeleted(item);
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.fetchFoodListData();
    }

    @Override
    public void displayData(final AddFoodListViewModel viewModel) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.setItems(viewModel.foodList);
                initSearchListener();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_food_menu, toolbar.getMenu());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Log.d(TAG, "onAddManually()");
                presenter.onAddManually();
                return true;
            case R.id.action_scan:
                Log.d(TAG, "onScan()");
                presenter.onAddScanner();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void initSearchListener() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filter(newText);
        return false;
    }

    @Override
    public void injectPresenter(AddFoodListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
