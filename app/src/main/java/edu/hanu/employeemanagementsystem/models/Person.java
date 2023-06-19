package edu.hanu.employeemanagementsystem.models;

import edu.hanu.employeemanagementsystem.exception.InvalidBirthdayException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmailException;
import edu.hanu.employeemanagementsystem.exception.InvalidEmployeeTypeException;
import edu.hanu.employeemanagementsystem.exception.InvalidFullNameException;
import edu.hanu.employeemanagementsystem.exception.InvalidPhoneException;

public abstract class Person {
    abstract public String getFullName();
    abstract public String getBirthDay();
    abstract public String getPhone();
    abstract public String getEmail();
    abstract public String getEmployeeType();
    abstract public Certificate getCertificate();
    abstract public void setFullName(String name) throws InvalidFullNameException;
    abstract public void setBirthDay(String birthDay) throws InvalidBirthdayException;
    abstract public void setPhone(String phone) throws InvalidPhoneException;
    abstract public void setEmail(String email) throws InvalidEmailException;
    abstract public void setEmployeeType(String employeeType) throws InvalidEmployeeTypeException;

    abstract public void setCertificate(Certificate certificate);
}
