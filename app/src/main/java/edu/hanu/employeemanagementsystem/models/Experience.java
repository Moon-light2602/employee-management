package edu.hanu.employeemanagementsystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Experience extends Employee implements Parcelable{
    private String expInYear;
    private String proSkill;
    private Certificate certificate;

    public Experience() {
    }

    public Experience(String name, String birthDay, String phone, String email, String employeeType, String expInYear, String proSkill) {
        super(name, birthDay, phone, email, employeeType);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    protected Experience(Parcel in) {
        fullName = in.readString();
        birthDay = in.readString();
        phone = in.readString();
        email = in.readString();
        employeeType = in.readString();
        expInYear = in.readString();
        proSkill = in.readString();
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    public String getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(String expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "fullName=" + fullName +
                "birthDay=" + birthDay +
                "certificateExp=" + certificate +
                ", expInYear='" + expInYear + '\'' +
                ", proSkill='" + proSkill + '\'' +
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
        parcel.writeString(expInYear);
        parcel.writeString(proSkill);
    }
}
