package sg.edu.rp.c346.appthree;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 16022738 on 28/3/2018.
 */

public class DutiesFrag extends Fragment {


    DatabaseHelper myDB;
    Button btnAdd, btnView;
    EditText addName, addDuty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragduties, container, false);


        addName = (EditText) view.findViewById(R.id.inputname);
        addDuty = (EditText) view.findViewById(R.id.duty);
        btnAdd = (Button) view.findViewById(R.id.addDuties);
        btnView = (Button) view.findViewById(R.id.viewDuties);
        myDB = new DatabaseHelper(getActivity());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addName.getText().toString();
                String duty = addDuty.getText().toString();
                if (name.length() != 0 && duty.length() !=0) {
                    AddDutyData(name, duty);
                    addName.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), duty_list.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void AddDutyData(String duty, String name){
        boolean insertData = myDB.addDutyData(duty, name);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(getContext() ,message, Toast.LENGTH_SHORT).show();
    }
}
