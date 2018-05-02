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

public class GuestFrag extends Fragment {

    DatabaseHelper myDB;
    Button btnAdd, btnView;
    EditText addName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragguestlist, container, false);



        addName = (EditText) view.findViewById(R.id.inputGuest);
        btnAdd = (Button) view.findViewById(R.id.addGuest);
        btnView= (Button) view.findViewById(R.id.viewGuest);
        myDB = new DatabaseHelper(getActivity());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gname = addName.getText().toString();
                if (gname.length() != 0 ) {
                    AddGuestData(gname);
                    addName.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), guest_list.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void AddGuestData(String gname){
        boolean insertData = myDB.addGuestData(gname);

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
