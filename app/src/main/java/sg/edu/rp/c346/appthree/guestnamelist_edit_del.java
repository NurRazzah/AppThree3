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

public class guestnamelist_edit_del extends AppCompatActivity{

    EditText editGuest, editPax;
    Button btnSave, btnDelete;
    DatabaseHelper myDB;


    String selectedName;
    int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guestnamelist_edit_del);


        editGuest = (EditText) findViewById(R.id.editTextName);
        btnSave = (Button) findViewById(R.id.saveBtn);
        btnDelete = (Button) findViewById(R.id.delBtn);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editPax.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editGuest.getText().toString();
                if (!item.equals("")) {
                    myDB.updateGuestName(item, selectedID, selectedName);
                } else {
                    toastMessage("You must enter a duty");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteName(selectedID, selectedName);
                editPax.setText("");
                toastMessage("removed from database");
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();


    }
}
