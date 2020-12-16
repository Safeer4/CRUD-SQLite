package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Read extends AppCompatActivity {

    //MyDatabaseHelper is user defined class and here we create an object of it.
    MyDatabaseHelper dbHelper;

    //SQLiteDatabase is pre-defined class and we create its object.
    SQLiteDatabase db;

    ListView lv;

    //onCreate method is called every time this activity is loaded, so this method has the code to read
//  data from the database and that code will be executed on every load.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


//        This code will run every time this activity is loaded
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