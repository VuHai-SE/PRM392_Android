package com.example.qlsv_se160835_net1717.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qlsv_se160835_net1717.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManagement.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STUDENT = "Student";
    public static final String COLUMN_STUDENT_ID = "ID";
    public static final String COLUMN_STUDENT_NAME = "name";
    public static final String COLUMN_STUDENT_DATE = "date";
    public static final String COLUMN_STUDENT_GENDER = "gender";
    public static final String COLUMN_STUDENT_EMAIL = "email";
    public static final String COLUMN_STUDENT_ADDRESS = "address";
    public static final String COLUMN_STUDENT_IDMAJOR = "idMajor";

    public static final String TABLE_MAJOR = "Major";
    public static final String COLUMN_MAJOR_ID = "idMajor";
    public static final String COLUMN_MAJOR_NAME = "nameMajor";

    private static final String TABLE_CREATE_STUDENT =
            "CREATE TABLE " + TABLE_STUDENT + " (" +
                    COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_STUDENT_NAME + " TEXT, " +
                    COLUMN_STUDENT_DATE + " TEXT, " +
                    COLUMN_STUDENT_GENDER + " TEXT, " +
                    COLUMN_STUDENT_EMAIL + " TEXT, " +
                    COLUMN_STUDENT_ADDRESS + " TEXT, " +
                    COLUMN_STUDENT_IDMAJOR + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_STUDENT_IDMAJOR + ") REFERENCES " + TABLE_MAJOR + "(" + COLUMN_MAJOR_ID + "));";

    private static final String TABLE_CREATE_MAJOR =
            "CREATE TABLE " + TABLE_MAJOR + " (" +
                    COLUMN_MAJOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MAJOR_NAME + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_MAJOR);
        db.execSQL(TABLE_CREATE_STUDENT);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAJOR);
        onCreate(db);
    }

    public void insertSampleData(SQLiteDatabase db) {
        if (!isStudentTableEmpty(db)) {
            return; // If the Student table is not empty, don't insert sample data
        }

        // Clear Major table
        db.execSQL("DELETE FROM " + TABLE_MAJOR);

        // Insert sample data into Major table
        ContentValues majorValues1 = new ContentValues();
        majorValues1.put(COLUMN_MAJOR_NAME, "Software Engineering");
        long majorId1 = db.insert(TABLE_MAJOR, null, majorValues1);

        ContentValues majorValues2 = new ContentValues();
        majorValues2.put(COLUMN_MAJOR_NAME, "Computer Science");
        long majorId2 = db.insert(TABLE_MAJOR, null, majorValues2);

        // Insert sample data into Student table
        ContentValues studentValues = new ContentValues();
        studentValues.put(COLUMN_STUDENT_NAME, "Vu Van Hai");
        studentValues.put(COLUMN_STUDENT_DATE, "2002-10-21");
        studentValues.put(COLUMN_STUDENT_GENDER, "Male");
        studentValues.put(COLUMN_STUDENT_EMAIL, "haivvse160835@fpt.edu.vn");
        studentValues.put(COLUMN_STUDENT_ADDRESS, "FPT University");
        studentValues.put(COLUMN_STUDENT_IDMAJOR, majorId1); // Assign majorId1 to this student
        db.insert(TABLE_STUDENT, null, studentValues);

        studentValues = new ContentValues();
        studentValues.put(COLUMN_STUDENT_NAME, "Bich Ngan");
        studentValues.put(COLUMN_STUDENT_DATE, "2002-08-06");
        studentValues.put(COLUMN_STUDENT_GENDER, "Female");
        studentValues.put(COLUMN_STUDENT_EMAIL, "Bich.Ngan@gmail.com");
        studentValues.put(COLUMN_STUDENT_ADDRESS, "Ho Chi Minh");
        studentValues.put(COLUMN_STUDENT_IDMAJOR, majorId2); // Assign majorId2 to this student
        db.insert(TABLE_STUDENT, null, studentValues);
    }

    private boolean isStudentTableEmpty(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_STUDENT, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count == 0;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_NAME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_DATE));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_GENDER));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_EMAIL));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ADDRESS));
                int idMajor = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_IDMAJOR));

                Student student = new Student(id, name, date, gender, email, address, idMajor);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, student.getName());
        values.put(COLUMN_STUDENT_DATE, student.getDate());
        values.put(COLUMN_STUDENT_GENDER, student.getGender());
        values.put(COLUMN_STUDENT_EMAIL, student.getEmail());
        values.put(COLUMN_STUDENT_ADDRESS, student.getAddress());
        values.put(COLUMN_STUDENT_IDMAJOR, student.getIdMajor());
        return db.insert(TABLE_STUDENT, null, values);
    }

    public List<String> getAllMajorNames() {
        List<String> majors = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MAJOR_NAME + " FROM " + TABLE_MAJOR, null);
        if (cursor.moveToFirst()) {
            do {
                majors.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAJOR_NAME)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return majors;
    }

    public int getMajorIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MAJOR_ID + " FROM " + TABLE_MAJOR + " WHERE " + COLUMN_MAJOR_NAME + "=?", new String[]{name});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAJOR_ID));
                cursor.close();
                return id;
            }
        }
        return -1; // Major not found
    }

    public int deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_STUDENT, COLUMN_STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, student.getName());
        values.put(COLUMN_STUDENT_DATE, student.getDate());
        values.put(COLUMN_STUDENT_GENDER, student.getGender());
        values.put(COLUMN_STUDENT_EMAIL, student.getEmail());
        values.put(COLUMN_STUDENT_ADDRESS, student.getAddress());
        values.put(COLUMN_STUDENT_IDMAJOR, student.getIdMajor());
        return db.update(TABLE_STUDENT, values, COLUMN_STUDENT_ID + " = ?", new String[]{String.valueOf(student.getId())});
    }

    public String getMajorName(int idMajor) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_MAJOR_NAME + " FROM " + TABLE_MAJOR + " WHERE " + COLUMN_MAJOR_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idMajor)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(COLUMN_MAJOR_NAME);
                if (columnIndex != -1) {
                    String majorName = cursor.getString(columnIndex);
                    cursor.close();
                    return majorName;
                }
            }
            cursor.close();
        }
        return "Unknown Major";
    }

    // Thêm các phương thức sau vào DatabaseHelper.java

    public long addMajor(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAJOR_NAME, name);
        return db.insert(TABLE_MAJOR, null, values);
    }

    public int deleteMajor(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MAJOR, COLUMN_MAJOR_NAME + " = ?", new String[]{name});
    }

    public int updateMajor(String oldName, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAJOR_NAME, newName);
        return db.update(TABLE_MAJOR, values, COLUMN_MAJOR_NAME + " = ?", new String[]{oldName});
    }


}
