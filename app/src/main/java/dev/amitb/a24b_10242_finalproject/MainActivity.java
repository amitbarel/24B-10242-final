package dev.amitb.a24b_10242_finalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import dev.amitb.groupedlistmodule.Item;
import dev.amitb.groupedlistmodule.SortCriteria;
import dev.amitb.groupedlistmodule.SortedListAdapter;
import dev.amitb.groupedlistmodule.SortedRecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SortedRecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> items = createSampleData();

        SortedListAdapter adapter = new SortedListAdapter(items);
        adapter.setSortCriteria(recyclerView.getSortCriteria());
        adapter.setSortCriteria(SortCriteria.BY_NAME);
        recyclerView.setAdapter(adapter);
    }

    private List<Item> createSampleData() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Apple", "Fruit"));
        items.add(new Item("Tomato", "Vegetable"));
        items.add(new Item("Mango", "Fruit"));
        items.add(new Item("Butter", "Dairy"));
        items.add(new Item("Banana", "Fruit"));
        items.add(new Item("Cucumber", "Vegetable"));
        items.add(new Item("Milk", "Dairy"));
        items.add(new Item("Carrot", "Vegetable"));
        return items;
    }
}