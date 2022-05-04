package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ex1.model.Exercise;
import com.example.ex1.model.ExerciseAdapter;
import com.example.ex1.model.SpinnerAdapter;

public class MainActivity extends AppCompatActivity implements ExerciseAdapter.ExerciseItemListener, View.OnClickListener {

    private Spinner spinner, spinnerUnit;
    private EditText edSearch;
    private Button btnAdd, btnUpdate, btnSearch, btnDelete;
    private RadioButton online;
    private RecyclerView recyclerView;
    private ExerciseAdapter adapter;

    public int p;
    private int[] imgs = {
            R.drawable.tcb,
            R.drawable.vcb,
            R.drawable.vib,
    };

    private final String[] serviceName = {
            "Techcombank", "Vietcombank", "vib"
    };

    private final String[] serviceUnit = {
            "1 tháng", "2 tháng", "3 tháng", "6 tháng", "9 tháng", "12 tháng"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.sp);
        spinnerUnit = findViewById(R.id.sp_unit);

        edSearch = findViewById(R.id.edSearch);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd = findViewById(R.id.btnAdd);
        online = findViewById(R.id.radio);
        recyclerView = findViewById(R.id.rview);
        adapter = new ExerciseAdapter(this);
        adapter.setClickListener(this);

        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        spinner.setAdapter(spinnerAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_text,
                serviceUnit);
        spinnerUnit.setAdapter(adapter);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        p = position;
        Exercise exercise = adapter.getItem(position);

        edPrice.setText(exercise.getPrice());
        int img = exercise.getImg();
        int ps = 0;
        for (int i =0; i <imgs.length; i++){
            if(img == imgs[i]){
                ps = i;
                break;
            }
        }
        for (int i =0; i <serviceUnit.length; i++){
            if(exercise.getUnit() == serviceUnit[i]){
                spinnerUnit.setSelection(i);
                break;
            }
        }
        spinner.setSelection(ps);

    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            Exercise exercise = new Exercise();
            String i = spinner.getSelectedItem().toString();
            String iUnit = spinnerUnit.getSelectedItem().toString();

            int img = 0;
            try {
                img = imgs[Integer.parseInt(i)];
            } catch (NumberFormatException e){

            }
            exercise.setImg(img);
            exercise.setName(iUnit);
            exercise.setUnit(iUnit);
            exercise.setPrice(edPrice.getText().toString().trim());
            if(exercise.getPrice().trim().isEmpty())
                Toast.makeText(this, "Khong duoc bo trong", Toast.LENGTH_SHORT).show();
            else
                adapter.add(exercise);

        }
        if (view == btnUpdate) {
            Exercise exercise = new Exercise();
            String i = spinner.getSelectedItem().toString();
            String iUnit = spinnerUnit.getSelectedItem().toString();

            int img = 0;
            try {
                img = imgs[Integer.parseInt(i)];
            } catch (NumberFormatException e){

            }
            exercise.setImg(img);
            exercise.setName(iUnit);
            exercise.setUnit(iUnit);
            exercise.setPrice(edPrice.getText().toString().trim());

            if(exercise.getName().trim().isEmpty())
                Toast.makeText(this, "Khong duoc bo trong", Toast.LENGTH_SHORT).show();
            else
                adapter.update(p, exercise);
        }

        if(view == btnDelete){
            adapter.delete(p);
        }

        if (view == btnSearch) {
            String name = edSearch.getText().toString().trim();
            adapter.search(name);
        }
    }
}