package edu.hanu.employeemanagementsystem.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hanu.employeemanagementsystem.EmployeeType;
import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmployeeTypeException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;
import edu.hanu.employeemanagementsystem.models.Certificate;
import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Experience;
import edu.hanu.employeemanagementsystem.models.Fresher;
import edu.hanu.employeemanagementsystem.models.Intern;

public class AddActivity extends AppCompatActivity{
    private String name, birthDay, phone, email, employeeType,expInYear, proSkill, gradDate, gradRank,
            gradEdu, major, semester, universityName, certificateName, certificateRank, certificateDate;
    private EditText edtName, edtBirthDay, edtPhone, edtEmail, edtEmployeeType, edtExpInYear, edtProSkill,
            edtGradDate, edtGradRank, edtGradEdu, edtMajor, edtSemester, edtUniversityName, edtCertificateName, edtCertificateRank, edtCertificateDate;
    private Button btnContinue, btnAdd, btnCancel;

    private ArrayList<Employee> employees = new ArrayList<>();

    private Employee experience = new Experience();
    private Employee fresher = new Fresher();
    private Employee intern = new Intern();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.employees.addAll(MainActivity.employees);

        initView();

        btnCancel.setOnClickListener(view -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnContinue.setOnClickListener(view -> {
            btnContinueClick();
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
        edtEmployeeType = findViewById(R.id.employeeType);

        //experience
        edtExpInYear = findViewById(R.id.edtExpInYear);
        edtProSkill = findViewById(R.id.edtProSkill);

        //fresher
        edtGradDate = findViewById(R.id.edtGradDate);
        edtGradRank = findViewById(R.id.edtGradRank);
        edtGradEdu = findViewById(R.id.edtGradEdu);

        //intern
        edtMajor = findViewById(R.id.edtMajor);
        edtSemester = findViewById(R.id.edtSemester);
        edtUniversityName = findViewById(R.id.edtUniversityName);

        //certificate
        edtCertificateDate = findViewById(R.id.edtCertificateDate);
        edtCertificateName = findViewById(R.id.edtCertificateName);
        edtCertificateRank = findViewById(R.id.edtCertificateRank);

        //button
        btnContinue = findViewById(R.id.btnContinue);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void btnContinueClick() {
        getGeneralInformation();

        if (checkEmployeeInfo()) {
            if(employeeType.equals("0")) {
                //hide default information
                setVisibility();

                //display some information of experience
                edtExpInYear.setVisibility(View.VISIBLE);
                edtProSkill.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);

            } else if(employeeType.equals("1")) {
                //hide default information
                setVisibility();

                //display some information of fresher
                edtGradDate.setVisibility(View.VISIBLE);
                edtGradRank.setVisibility(View.VISIBLE);
                edtGradEdu.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);

            } else if(employeeType.equals("2")) {
                //hide default information
                setVisibility();

                //display some information of intern
                edtMajor.setVisibility(View.VISIBLE);
                edtSemester.setVisibility(View.VISIBLE);
                edtUniversityName.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
            }
        }
    }

    private void btnAddClick() {
        switch (employeeType) {
            case "0":
                Experience experience1 = (Experience) experience;
                expInYear = edtExpInYear.getText().toString();
                proSkill = edtProSkill.getText().toString();
                getCertificate();
                experience1.setExpInYear(expInYear);
                experience1.setProSkill(proSkill);
                experience1.setCertificate(new Certificate(certificateName, certificateRank, certificateDate));

                employees.add(experience1);
                break;
            case "1":
                Fresher fresher1 = (Fresher) fresher;
                gradDate = edtGradDate.getText().toString();
                gradRank = edtGradRank.getText().toString();
                gradEdu = edtGradEdu.getText().toString();
                getCertificate();
                fresher1.setGraduationDate(gradDate);
                fresher1.setGraduationRank(Integer.parseInt(gradRank));
                fresher1.setEducation(gradEdu);
                fresher1.setCertificate(new Certificate(certificateName, certificateRank, certificateDate));

                employees.add(fresher1);
                break;
            case "2":
                Intern intern1 = (Intern) intern;
                major = edtMajor.getText().toString();
                semester = edtSemester.getText().toString();
                universityName = edtUniversityName.getText().toString();
                getCertificate();
                intern1.setMajor(major);
                intern1.setSemester(Integer.parseInt(semester));
                intern1.setUniversityName(universityName);
                intern1.setCertificate(new Certificate(certificateName, certificateRank, certificateDate));

                employees.add(intern1);
                break;
        }

        MainActivity.employees.clear();
        MainActivity.employees.addAll(employees);
        finish();
    }

    private void getGeneralInformation() {
        name = edtName.getText().toString();
        birthDay = edtBirthDay.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        employeeType = edtEmployeeType.getText().toString();
        getCertificate();
    }

    private void getCertificate() {
        certificateName = edtCertificateName.getText().toString();
        certificateRank = edtCertificateRank.getText().toString();
        certificateDate = edtCertificateDate.getText().toString();
    }

    private boolean checkEmployeeInfo() {
        if (employeeType.equals(String.valueOf(EmployeeType.EXPERIENCE.ordinal()))) {
            try {
                experience.setFullName(name);
                experience.setBirthDay(birthDay);
                experience.setPhone(phone);
                experience.setEmail(email);
                experience.setEmployeeType(employeeType);
                return true;
            } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException |
                     InvalidEmailException | InvalidEmployeeTypeException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (employeeType.equals(String.valueOf(EmployeeType.FRESHER.ordinal()))) {
            try {
                fresher.setFullName(name);
                fresher.setBirthDay(birthDay);
                fresher.setPhone(phone);
                fresher.setEmail(email);
                fresher.setEmployeeType(employeeType);
                return true;
            } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException |
                     InvalidEmailException | InvalidEmployeeTypeException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (employeeType.equals(String.valueOf(EmployeeType.INTERN.ordinal()))) {
            try {
                intern.setFullName(name);
                intern.setBirthDay(birthDay);
                intern.setPhone(phone);
                intern.setEmail(email);
                intern.setEmployeeType(employeeType);
                return true;
            } catch (InvalidFullNameException | InvalidBirthdayException | InvalidPhoneException |
                     InvalidEmailException | InvalidEmployeeTypeException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    private void setVisibility() {
        edtName.setVisibility(View.INVISIBLE);
        edtBirthDay.setVisibility(View.INVISIBLE);
        edtPhone.setVisibility(View.INVISIBLE);
        edtEmail.setVisibility(View.INVISIBLE);
        edtEmployeeType.setVisibility(View.INVISIBLE);
        edtCertificateName.setVisibility(View.INVISIBLE);
        edtCertificateRank.setVisibility(View.INVISIBLE);
        edtCertificateDate.setVisibility(View.INVISIBLE);
        btnContinue.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
    }
}
