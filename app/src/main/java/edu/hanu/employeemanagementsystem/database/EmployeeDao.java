package edu.hanu.employeemanagementsystem.database;

import static edu.hanu.employeemanagementsystem.database.EmployeeDatabase.TABLE_NAME;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


import edu.hanu.employeemanagementsystem.models.Employee;
@Dao
public interface EmployeeDao {

    @Insert
    void insertEmployee(Employee employee);

    @Query("SELECT * FROM " + TABLE_NAME)
    List<Employee> getListEmployee();

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);
}
