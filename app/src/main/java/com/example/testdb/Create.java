package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends AppCompatActivity {

    //MyDatabaseHelper is user defined class and here we create an object of it.
    MyDatabaseHelper dbHelper;

    //SQLiteDatabase is pre-defined class and we create its object.
    SQLiteDatabase db;

    //We take EditText and ListView objects so we can take data from editText and show our
    //database data in listView.
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    //This method is triggered on insert button, it gets data from EditText and then insert that
    //data into a table in database;
    public void insert(View view) {

        //We initialize et object by assigning it its respective EditText field
        et = (EditText)findViewById(R.id.name);

        //The data we take from EditText is stored in name variable.
        String name = et.getText().toString();

        //dbHelper is initialized
        dbHelper = new MyDatabaseHelper(this);

        //db is initialized for writable, because we will write data to the database
        db = dbHelper.getWritableDatabase();

        //ContentValues is used for inserting data into the database, we create its object cv
        ContentValues cv = new ContentValues();

        //.put() method is used to add values to ContentValues object
        //the first parameter is column name and the second is the value
        cv.put("StudentName", name);

        //.insert() method is used to insert values to the database. It takes three parameters
        //first is table name, second is nullColumnHack (We write name of any column in this parameter
        // if we want to insert an empty row) and third parameter is the ContentValues object which has values.
        //And insert() method return the row ID which it inserted right now. Here we assign that row ID to a
        //long type variable rowId.
        long rowId = db.insert("Student", null, cv);

        //We show the inserted row ID in a toast message for the confirmation that row is inserted.
        Toast.makeText(this, Long.toString(rowId), Toast.LENGTH_SHORT).show();
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}