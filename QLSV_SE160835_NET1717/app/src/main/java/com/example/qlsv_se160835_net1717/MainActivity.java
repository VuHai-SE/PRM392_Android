package com.example.qlsv_se160835_net1717;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv_se160835_net1717.database.DatabaseHelper;
import com.example.qlsv_se160835_net1717.model.Student;
import com.example.qlsv_se160835_net1717.ui.StudentAdapter;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.StudentAdapterListener {

    private static final String TAG = "MainActivity";
    private DatabaseHelper dbHelper;
    private ListView listViewStudents;
    private Button buttonAddStudent, buttonManageMajor;
    private List<Student> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewStudents = findViewById(R.id.listViewStudents);
        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        buttonManageMajor = findViewById(R.id.buttonManageMajor);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Ensure the database is writable
        dbHelper.insertSampleData(db); // Insert sample data if the table is empty

        studentList = dbHelper.getAllStudents();

        if (studentList.isEmpty()) {
            Log.e(TAG, "No students found in database.");
        } else {
            for (Student student : studentList) {
                Log.d(TAG, "Student: " + student.getName());
            }
        }

        adapter = new StudentAdapter(this, R.layout.list_item_student, studentList, this);
        listViewStudents.setAdapter(adapter);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddStudentDialog();
            }
        });

        buttonManageMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MajorManagementActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onEditClicked(Student student) {
        showEditStudentDialog(student);
    }

    @Override
    public void onDeleteClicked(Student student) {
        dbHelper.deleteStudent(student.getId());
        studentList.remove(student);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private void showAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_student, null);
        builder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextAddress = dialogView.findViewById(R.id.editTextAddress);
        final EditText editTextDate = dialogView.findViewById(R.id.editTextDate);
        final Spinner spinnerGender = dialogView.findViewById(R.id.spinnerGender);
        final Spinner spinnerMajor = dialogView.findViewById(R.id.spinnerMajor);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(editTextDate);
            }
        });
        
        List<String> majors = dbHelper.getAllMajorNames();
        ArrayAdapter<String> majorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majors);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(majorAdapter);

        final Button buttonAdd = dialogView.findViewById(R.id.buttonAdd);
        final AlertDialog dialog = builder.create();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String gender = spinnerGender.getSelectedItem().toString();
                String major = spinnerMajor.getSelectedItem().toString();

                if (name.isEmpty() || email.isEmpty() || address.isEmpty() || date.isEmpty() || gender.isEmpty() || major.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add new student to the database
                int majorId = dbHelper.getMajorIdByName(major);
                if (majorId == -1) {
                    Toast.makeText(MainActivity.this, "Invalid major", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student(name, date, gender, email, address, majorId);
                long result = dbHelper.addStudent(student);
                if (result == -1) {
                    Toast.makeText(MainActivity.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                } else {
                    studentList.add(student);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    private void showEditStudentDialog(final Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_student, null);
        builder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextAddress = dialogView.findViewById(R.id.editTextAddress);
        final EditText editTextDate = dialogView.findViewById(R.id.editTextDate);
        final Spinner spinnerGender = dialogView.findViewById(R.id.spinnerGender);
        final Spinner spinnerMajor = dialogView.findViewById(R.id.spinnerMajor);
        final Button buttonAdd = dialogView.findViewById(R.id.buttonAdd);

        // Set the fields with the current student data
        editTextName.setText(student.getName());
        editTextEmail.setText(student.getEmail());
        editTextAddress.setText(student.getAddress());
        editTextDate.setText(student.getDate());

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);
        if (student.getGender().equals("Male")) {
            spinnerGender.setSelection(0);
        } else {
            spinnerGender.setSelection(1);
        }

        List<String> majors = dbHelper.getAllMajorNames();
        ArrayAdapter<String> majorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majors);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(majorAdapter);
        spinnerMajor.setSelection(majors.indexOf(dbHelper.getMajorName(student.getIdMajor())));

        // Change button text to "Update"
        buttonAdd.setText("Update");

        final AlertDialog dialog = builder.create();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String gender = spinnerGender.getSelectedItem().toString();
                String major = spinnerMajor.getSelectedItem().toString();

                if (name.isEmpty() || email.isEmpty() || address.isEmpty() || date.isEmpty() || gender.isEmpty() || major.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update student data
                student.setName(name);
                student.setEmail(email);
                student.setAddress(address);
                student.setDate(date);
                student.setGender(gender);
                student.setIdMajor(dbHelper.getMajorIdByName(major));

                dbHelper.updateStudent(student);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDatePicker(final EditText editTextDate) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        editTextDate.setText(date);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

}
