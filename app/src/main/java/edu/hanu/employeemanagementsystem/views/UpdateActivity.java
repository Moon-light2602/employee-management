package edu.hanu.employeemanagementsystem.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.adapters.EmployeeAdapter;
import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Experience;
import edu.hanu.employeemanagementsystem.models.Fresher;
import edu.hanu.employeemanagementsystem.models.Intern;
import edu.hanu.employeemanagementsystem.views.MainActivity;

public class UpdateActivity extends AppCompatActivity {
    private String name, birthDay, phone, email, employeeType, expInYear, proSkill, gradDate, gradEdu, major, universityName;
    private int gradRank, semester;
    EditText name4, birthDay4, phone4, email4, employeeType4, expInYear4, proSkill4, gradDate4, gradRank4, gradEdu4, major4, semester4, universityName4;
    Button btnUpdate, btnCancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();

        getInformationFromAddActivity();

        btnCancel.setOnClickListener(view -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnUpdate.setOnClickListener(view -> {
            btnUpdateClick();
        });
    }

    private void getInformationFromAddActivity() {
        Employee employee = getIntent().getParcelableExtra("employee");
        if (employee instanceof Experience) {
            Experience experience = getIntent().getParcelableExtra("employee");

            setGeneralInformation(experience);
            expInYear4.setText(experience.getExpInYear());
            proSkill4.setText(experience.getProSkill());
        }
        if (employee instanceof Intern) {
            setVisibility();

            major4.setVisibility(View.VISIBLE);
            semester4.setVisibility(View.VISIBLE);
            universityName4.setVisibility(View.VISIBLE);
            Intern intern = getIntent().getParcelableExtra("employee");

            setGeneralInformation(intern);
            major4.setText(intern.getMajor());
            semester4.setText(""+intern.getSemester());
            universityName4.setText(intern.getUniversityName());
        }
        if (employee instanceof Fresher) {
            setVisibility();
            gradDate4.setVisibility(View.VISIBLE);
            gradRank4.setVisibility(View.VISIBLE);
            gradEdu4.setVisibility(View.VISIBLE);

            major4.setVisibility(View.INVISIBLE);
            semester4.setVisibility(View.INVISIBLE);
            universityName4.setVisibility(View.INVISIBLE);
            Fresher fresher = getIntent().getParcelableExtra("employee");

            setGeneralInformation(fresher);
            gradDate4.setText(fresher.getGraduationDate());
            gradRank4.setText(""+fresher.getGraduationRank());
            gradEdu4.setText(fresher.getEducation());
        }
    }

    private void btnUpdateClick() {
        employeeType = employeeType4.getText().toString();
        if(employeeType.equals("0")) {
            getGeneralInformation();
            expInYear = expInYear4.getText().toString();
            proSkill = proSkill4.getText().toString();
            Experience experience = new Experience(name, birthDay, phone, email, employeeType, expInYear, proSkill);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("employee1", experience);
            setResult(RESULT_OK, intent);
            finish();
        } else if(employeeType.equals("1")) {
            getGeneralInformation();
            gradDate = gradDate4.getText().toString();
            gradRank = Integer.parseInt(gradRank4.getText().toString());
            gradEdu = gradEdu4.getText().toString();
            Fresher fresher = new Fresher(name, birthDay, phone, email, employeeType, gradDate, gradRank, gradEdu);

            Intent intent = new Intent();
            intent.putExtra("employee1", fresher);
            setResult(RESULT_OK, intent);
            finish();
        } else if(employeeType.equals("2")) {
            getGeneralInformation();
            major = major4.getText().toString();
            semester = Integer.parseInt(semester4.getText().toString());
            universityName = universityName4.getText().toString();
            Intern intern = new Intern(name, birthDay, phone, email, employeeType, major, semester, universityName);

            Intent intent = new Intent();
            intent.putExtra("employee1", intern);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void initView() {
        name4 = findViewById(R.id.name4);
        birthDay4 = findViewById(R.id.birthDay4);
        phone4 = findViewById(R.id.phone4);
        email4 = findViewById(R.id.email4);
        employeeType4 = findViewById(R.id.employeeType4);

        //experience information
        expInYear4 = findViewById(R.id.edtExpInYear4);
        proSkill4 = findViewById(R.id.edtProSkill4);

        //fresher information
        gradDate4 = findViewById(R.id.edtGradDate4);
        gradRank4 = findViewById(R.id.edtGradRank4);
        gradEdu4 = findViewById(R.id.edtGradEdu4);

        //intern information
        major4 = findViewById(R.id.edtMajor4);
        semester4 = findViewById(R.id.edtSemester4);
        universityName4 = findViewById(R.id.edtUniversityName4);

        //button
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel1);
    }

    private void setVisibility() {
        name4.setVisibility(View.VISIBLE);
        birthDay4.setVisibility(View.VISIBLE);
        phone4.setVisibility(View.VISIBLE);
        email4.setVisibility(View.VISIBLE);
        employeeType4.setVisibility(View.VISIBLE);
        expInYear4.setVisibility(View.INVISIBLE);
        proSkill4.setVisibility(View.INVISIBLE);
    }

    private void getGeneralInformation() {
        name = name4.getText().toString();
        birthDay = birthDay4.getText().toString();
        phone = phone4.getText().toString();
        email = email4.getText().toString();
        employeeType = employeeType4.getText().toString();
    }

    private void setGeneralInformation(Employee employee) {
        name4.setText(employee.getFullName());
        birthDay4.setText(employee.getBirthDay());
        phone4.setText(employee.getPhone());
        email4.setText(employee.getEmail());
        employeeType4.setText(employee.getEmployeeType());
    }
}