package sg.edu.rp.c346.id20019018.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinAOR;
    EditText etToDo;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView LVtasks;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinAOR = findViewById(R.id.spinner);
        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDel);
        btnClear = findViewById(R.id.buttonClear);
        LVtasks = findViewById(R.id.ListViewTask);

        alTasks = new ArrayList<>();

        aaTasks = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,alTasks);

        LVtasks.setAdapter(aaTasks);

        spinAOR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    btnAdd.setEnabled(true);
                    btnDel.setEnabled(false);
                    etToDo.setText("");
                    etToDo.setHint("Type in a new task here");
                    etToDo.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else if(position == 1)
                {
                    btnAdd.setEnabled(false);
                    btnDel.setEnabled(true);
                    etToDo.setText("");
                    etToDo.setHint("Type in the index of the task to be removed");
                    etToDo.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etToDo.getText().toString().trim();
                if(etToDo.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "You Need To Enter A Task!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    alTasks.add(task);
                }
                aaTasks.notifyDataSetChanged();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -1;
                if(!etToDo.getText().toString().trim().isEmpty())
                {
                    index = Integer.parseInt(etToDo.getText().toString());
                    index -= 1;
                    if(alTasks.size() != 0)
                    {
                        if(index >= alTasks.size() || index < 0)
                        {
                            Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            alTasks.remove(index);
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "You Don't Have Any Task To Delete", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You Need To Enter An Index!", Toast.LENGTH_SHORT).show();
                }
                aaTasks.notifyDataSetChanged();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.size() != 0)
                {
                    alTasks.clear();
                    Toast.makeText(MainActivity.this, "Everything Has Been Cleared", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "There Are No Tasks", Toast.LENGTH_SHORT).show();
                }
                aaTasks.notifyDataSetChanged();
            }
        });
        LVtasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String removed = alTasks.get(position);
                alTasks.remove(position);
                Toast.makeText(MainActivity.this, removed + " Has Been Removed", Toast.LENGTH_SHORT).show();
                aaTasks.notifyDataSetChanged();
            }
        });

    }
}