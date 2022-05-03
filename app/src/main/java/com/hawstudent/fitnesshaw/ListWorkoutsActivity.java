package com.hawstudent.fitnesshaw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListWorkoutsActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        listView = (ListView) findViewById(R.id.listviewWorkouts);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Bench Press");
        arrayList.add("Cable Crossover");
        arrayList.add("Weighted Dips");
        arrayList.add("Low Cable Crossover");
        arrayList.add("Incline Press");
        arrayList.add("Close Grip Pull Down");
        arrayList.add("One Arm Dumbbell Row");
        arrayList.add("Bench Press");
        arrayList.add("Cable Crossover");
        arrayList.add("Weighted Dips");
        arrayList.add("Low Cable Crossover");
        arrayList.add("Incline Press");
        arrayList.add("Close Grip Pull Down");
        arrayList.add("One Arm Dumbbell Row");
        arrayList.add("Bench Press");
        arrayList.add("Cable Crossover");
        arrayList.add("Weighted Dips");
        arrayList.add("Low Cable Crossover");
        arrayList.add("Incline Press");
        arrayList.add("Close Grip Pull Down");
        arrayList.add("One Arm Dumbbell Row");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListWorkoutsActivity.this, "clicked item: " + i + " " + arrayList.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}