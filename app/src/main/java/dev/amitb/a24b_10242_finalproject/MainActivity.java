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

    private SortedRecyclerView recyclerView;
    private SortedListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> items = createSampleData();

        adapter = new SortedListAdapter(items);
        adapter.setSortCriteria(recyclerView.getSortCriteria());
        adapter.setSortCriteria(SortCriteria.BY_PRICE);
        recyclerView.setAdapter(adapter);
    }

    private List<Item> createSampleData() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Apple", "Fruit", 1.20));
        items.add(new Item("Tomato", "Vegetable", 0.85));
        items.add(new Item("Mango", "Fruit", 1.75));
        items.add(new Item("Butter", "Dairy", 2.80));
        items.add(new Item("Banana", "Fruit", 0.75));
        items.add(new Item("Cucumber", "Vegetable", 0.90));
        items.add(new Item("Milk", "Dairy", 1.40));
        items.add(new Item("Carrot", "Vegetable", 1.00));
        return items;
    }
}