package sg.edu.rp.c346.appthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 16022738 on 3/4/2018.
 */

public class task_edit_del extends AppCompatActivity{
    EditText editTask;
    Button btnSave, btnDelete;


    String selectedTask;
    int selectedID;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_edit_del);


        editTask = (EditText) findViewById(R.id.editTextTask);
        btnSave = (Button) findViewById(R.id.saveBtn);
        btnDelete = (Button) findViewById(R.id.delBtn);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedTask = receivedIntent.getStringExtra("task");

        //set the text to show the current selected name
        editTask.setText(selectedTask);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editTask.getText().toString();
                if (!item.equals("")) {
                    myDB.updateTask(item, selectedID, selectedTask);
                } else {
                    toastMessage("You must enter a duty");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteName(selectedID, selectedTask);
                editTask.setText("");
                toastMessage("removed from database");
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();


    }
}
