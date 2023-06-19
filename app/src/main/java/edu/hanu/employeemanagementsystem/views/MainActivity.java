package edu.hanu.employeemanagementsystem.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.adapters.EmployeeAdapter;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmployeeTypeException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;
import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Experience;
import edu.hanu.employeemanagementsystem.models.Fresher;
import edu.hanu.employeemanagementsystem.models.Intern;

public class MainActivity extends AppCompatActivity implements EmployeeListener {
    private static int REQUEST_CODE = 6969;
    private EmployeeAdapter adapter;
    SearchView searchView;
    public static ArrayList<Employee> employees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycleView = findViewById(R.id.recycleView);

        searchView = findViewById(R.id.search);

        searchEmployee();

        adapter = new EmployeeAdapter(MainActivity.this, employees, this);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void employeeClick(Employee employee, int index) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("employee", employee);
        startActivityForResult(intent, REQUEST_CODE);
    }



    private void searchEmployee() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }


    private void setData(Employee employee){
        try {
            employee.setFullName(employee.getFullName());
            employee.setBirthDay(employee.getBirthDay());
            employee.setPhone(employee.getPhone());
            employee.setEmail(employee.getEmail());
            employee.setEmployeeType(employee.getEmployeeType());
        } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException |
                 InvalidEmailException | InvalidEmployeeTypeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}