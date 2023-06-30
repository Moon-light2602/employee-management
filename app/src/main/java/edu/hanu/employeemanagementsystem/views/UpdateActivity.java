package edu.hanu.employeemanagementsystem.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.database.EmployeeDatabase;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;
import edu.hanu.employeemanagementsystem.models.Employee;

public class UpdateActivity extends AppCompatActivity {
    private String name, birthDay, phone, email;
    EditText name4, birthDay4, phone4, email4;
    Button btnUpdate, btnCancel;

    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();

        employee = (Employee) getIntent().getExtras().get("updateEmployee");
        setGeneralInformation(employee);

        btnCancel.setOnClickListener(view -> {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnUpdate.setOnClickListener(view -> {
            btnUpdateClick();
        });
    }


    private void btnUpdateClick() {
        name = name4.getText().toString();
        birthDay = birthDay4.getText().toString();
        phone = phone4.getText().toString();
        email = email4.getText().toString();

        try {
            employee.setFullName(name);
            employee.setBirthDay(birthDay);
            employee.setPhone(phone);
            employee.setEmail(email);
        } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException | InvalidEmailException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        EmployeeDatabase.getInstance(this).getEmployeeDao().updateEmployee(employee);
        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    private void initView() {
        name4 = findViewById(R.id.name4);
        birthDay4 = findViewById(R.id.birthDay4);
        phone4 = findViewById(R.id.phone4);
        email4 = findViewById(R.id.email4);

        //button
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel1);
    }
    private void setGeneralInformation(Employee employee) {
        name4.setText(employee.getFullName());
        birthDay4.setText(employee.getBirthDay());
        phone4.setText(employee.getPhone());
        email4.setText(employee.getEmail());
    }
}