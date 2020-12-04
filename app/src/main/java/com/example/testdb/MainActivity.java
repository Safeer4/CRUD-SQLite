package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //MyDatabaseHelper is user defined class and here we create an object of it.
    MyDatabaseHelper dbHelper;

    //SQLiteDatabase is pre-defined class and we create its object.
    SQLiteDatabase db;

    //We take EditText and ListView objects so we can take data from editText and show our
    //database data in listView.
    EditText et;
    ListView lv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    //This method is triggered on read button, it retrieves data from the database
    //and show it in a ListView
    public void read(View view) {
        //dbHelper is initialized
        dbHelper = new MyDatabaseHelper(this);

        //db is initialized for readable, because we will read data from database
        db = dbHelper.getReadableDatabase();

        //We created an ArrayList (A dynamic array) by the name of students
        ArrayList students = new ArrayList();

        //We write a query which selects whole data from Student table
        String query = "SELECT * FROM Student";

        //We created a Cursor object by the name cursor, because the data returned from the database
        //is in cursor format.
        //rawQuery() method is used to execute the query and retrieve data from the database
        Cursor cursor = db.rawQuery(query, null);

        //If any data is retrieved, the cursor position will be moved to the first value
        if (cursor.moveToFirst())
        {

            //We loop through the data returned by the rawQuery, which is stored in cursor
            do {
                //Each value of the "StudentName" column is stored in the name variable
                String name = cursor.getString(cursor.getColumnIndex("StudentName"));

                //We add the data into the students (ArrayList).
                students.add(name);
            }
            while(cursor.moveToNext());

            //We close the DB connection after our data is retrieved
            db.close();
        }

        //We find the ListView by id "lview" and assign it to lv
        lv = (ListView)findViewById(R.id.lview);

        //We create an ArrayAdapter object adapter, because we want to show data dynamically in ListView
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, students);

        //The adapter is set to the ListView
        lv.setAdapter(adapter);
    }
}