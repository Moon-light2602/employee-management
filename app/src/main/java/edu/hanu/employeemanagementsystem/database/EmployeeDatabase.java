package edu.hanu.employeemanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.hanu.employeemanagementsystem.models.Employee;

@Database(entities = {Employee.class}, version = 3)
public abstract class EmployeeDatabase extends RoomDatabase {
    public static final String TABLE_NAME ="employee";
    private static final String DATABASE_NAME = "employee.db";
    private static EmployeeDatabase instance;

    public static synchronized EmployeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract EmployeeDao getEmployeeDao();
}
