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

public class AddActivity extends AppCompatActivity{
    private String name, birthDay, phone, email;
    private EditText edtName, edtBirthDay, edtPhone, edtEmail;
    private Button btnAdd, btnCancel;

    private Employee employee = new Employee();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        btnCancel.setOnClickListener(view -> {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            finish();
        });


        btnAdd.setOnClickListener(view -> {
            btnAddClick();
        });
    }


    private void initView() {
        edtName = findViewById(R.id.name);
        edtBirthDay = findViewById(R.id.birthDay);
        edtPhone = findViewById(R.id.phone);
        edtEmail = findViewById(R.id.email);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void btnAddClick() {
        name = edtName.getText().toString();
        birthDay = edtBirthDay.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();

        if(checkEmployeeInfo()){
            Employee employee = new Employee(name, birthDay, phone, email);
            EmployeeDatabase.getInstance(this).getEmployeeDao().insertEmployee(employee);
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    private boolean checkEmployeeInfo() {
        try{
            employee.setFullName(name);
            employee.setBirthDay(birthDay);
            employee.setPhone(phone);
            employee.setEmail(email);
            return true;
        } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException | InvalidEmailException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void setVisibility() {
        edtName.setVisibility(View.INVISIBLE);
        edtBirthDay.setVisibility(View.INVISIBLE);
        edtPhone.setVisibility(View.INVISIBLE);
        edtEmail.setVisibility(View.INVISIBLE);
        btnAdd.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
    }
}
