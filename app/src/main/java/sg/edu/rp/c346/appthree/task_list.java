package sg.edu.rp.c346.appthree;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 16022738 on 3/4/2018.
 */

public class task_list extends AppCompatActivity{


    DatabaseHelper myDB;

    private ListView taskListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
        taskListView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {

        //get the data and append to a list
        Cursor data = myDB.getTaskData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        taskListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String task = adapterView.getItemAtPosition(i).toString();

                Cursor data = myDB.getTaskID(task); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Intent editScreenIntent = new Intent(task_list.this, task_edit_del.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("task",task);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that task");
                }
            }
        });
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
