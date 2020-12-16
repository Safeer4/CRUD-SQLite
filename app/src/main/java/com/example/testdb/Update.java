package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    //MyDatabaseHelper is user defined class and here we create an object of it.
    MyDatabaseHelper dbHelper;

    //SQLiteDatabase is pre-defined class and we create its object.
    SQLiteDatabase db;

    EditText currentName, newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        currentName = (EditText)findViewById(R.id.currentName);
        newName = (EditText)findViewById(R.id.newName);
    }

    public void update(View view) {
        String current = currentName.getText().toString();
        String newUser = newName.getText().toString();

        //dbHelper is initialized
        dbHelper = new MyDatabaseHelper(this);

        //db is initialized for writable, because we will write data to the database
        db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("StudentName", newUser);

        String currentUser[] = {current};

        int rows = db.update("Student", cv, "StudentName=?", currentUser);
        db.close();

        Toast.makeText(this, Integer.toString(rows) + " rows updated", Toast.LENGTH_SHORT).show();

    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}