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

public class TaskFrag extends Fragment {

    Button btnAdd, btnView;
    EditText inputTask;
    DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragtask, container, false);


        inputTask = (EditText) view.findViewById(R.id.inputTask);
        btnAdd = (Button) view.findViewById(R.id.addtask);
        btnView= (Button) view.findViewById(R.id.viewtask);
        myDB = new DatabaseHelper(getActivity());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputTask.getText().toString();
                if (name.length() != 0) {
                    AddTaskData(name);
                    inputTask.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), task_list.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void AddTaskData(String task){
        boolean insertData = myDB.addTaskData(task);

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
