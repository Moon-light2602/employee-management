package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmployeeTypeException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;

public class Employee extends Person implements Parcelable{
    private String birthDayPattern = "^([0-2]\\d||3[0-1])/(0\\d||1[0-2])/(\\d\\d)?\\d\\d$";
    private String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[ _A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[ A-Za-z0-9]+)*(\\.[ A-Za-z]{2,})$";
    private String phonePattern = "[0-9]{10,16}";

    private int idEmployee;
    public String fullName;
    public String birthDay;
    public String phone;
    public String email;
    public String employeeType;
    private Certificate certificate;

    public Employee(String fullName, String birthDay, String phone, String email, String employeeType) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.employeeType = employeeType;
    }


    protected Employee(Parcel in) {
        idEmployee = in.readInt();
        fullName = in.readString();
        birthDay = in.readString();
        phone = in.readString();
        email = in.readString();
        employeeType = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in) {

            };
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public Employee() {
    }

    public int getId() {
        return idEmployee;
    }

    public void setId(int id) {
        this.idEmployee = id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) throws InvalidFullNameException {
        if (fullName.isEmpty()) {
            throw new InvalidFullNameException("Full name is required.");
        } else {
            this.fullName = fullName;
        }
    }

    @Override
    public String getBirthDay() {
        return birthDay;
    }

    @Override
    public void setBirthDay(String birthDay) throws InvalidBirthdayException{
        if(!birthDay.matches(birthDayPattern)){
            throw new InvalidBirthdayException("Birthday is invalid. Example: 12/12/1988");
        } else if(birthDay.isEmpty()) {
            throw new InvalidBirthdayException("Birthday is required");
        } else {
            this.birthDay = birthDay;
        }
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) throws InvalidPhoneException {
        if(!phone.matches(phonePattern)) {
            throw new InvalidPhoneException("Phone number is invalid. Example: 0988676003");
        } else if(phone.isEmpty()) {
            throw new InvalidPhoneException("Phone is required");
        } else {
            this.phone = phone;
        }
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) throws InvalidEmailException{
        if(!email.matches(emailPattern)) {
            throw new InvalidEmailException("Email is invalid. Example: thanhhh@gmail.com");
        } else if(email.isEmpty()) {
            throw new InvalidEmailException("Email is required");
        } else {
            this.email = email;
        }
    }

    @Override
    public String getEmployeeType() {
        return employeeType;
    }

    @Override
    public void setEmployeeType(String employeeType) throws InvalidEmployeeTypeException {
        if(employeeType.isEmpty()) {
            throw new InvalidEmployeeTypeException("EmployeeType is required.");
        } else {
            this.employeeType = employeeType;
        }
    }

    @Override
    public Certificate getCertificate() {
        return certificate;
    }

    @Override
    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + idEmployee +
                ", fullName='" + fullName + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(idEmployee);
        parcel.writeString(fullName);
        parcel.writeString(birthDay);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(employeeType);
    }
}
