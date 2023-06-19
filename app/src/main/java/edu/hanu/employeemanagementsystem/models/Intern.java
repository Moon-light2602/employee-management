package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Intern extends Employee implements Parcelable {
    private String major;
    private int semester;
    private String universityName;
    private Certificate certificate;

    public Intern() {

    }

    public Intern(String fullName, String birthDay, String phone, String email, String employeeType, String major, int semester, String universityName) {
        super(fullName, birthDay, phone, email, employeeType);
        this.major = major;
        this.semester = semester;
        this.universityName = universityName;
    }

    protected Intern(Parcel in) {
        fullName = in.readString();
        birthDay = in.readString();
        phone = in.readString();
        email = in.readString();
        employeeType = in.readString();
        major = in.readString();
        semester = in.readInt();
        universityName = in.readString();
    }

    public static final Creator<Intern> CREATOR = new Creator<Intern>() {
        @Override
        public Intern createFromParcel(Parcel in) {
            return new Intern(in);
        }

        @Override
        public Intern[] newArray(int size) {
            return new Intern[size];
        }
    };

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Intern{" +
                "certificateIntern=" + certificate +
                ", major='" + major + '\'' +
                ", semester=" + semester +
                ", universityName='" + universityName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(birthDay);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(employeeType);
        parcel.writeString(major);
        parcel.writeInt(semester);
        parcel.writeString(universityName);
    }
}
