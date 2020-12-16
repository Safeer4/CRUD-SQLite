package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void create(View view) {
        Intent intent = new Intent(this, Create.class);
        startActivity(intent);
    }

    public void read(View view) {
        Intent intent = new Intent(this, Read.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(this, Update.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(this, Delete.class);
        startActivity(intent);
    }

    public void deleteAll(View view) {
        dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        db.execSQL("DROP Table Student");
        Toast.makeText(this, "All data deleted", Toast.LENGTH_SHORT).show();
        dbHelper.onCreate(db);
    }
    EditText ed;
    public void checkLogin(View view) {

        ed = (EditText)findViewById(R.id.checkId);
        Boolean bool = false;

        String name = ed.getText().toString();

        dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from Student", null);

        if (cursor.moveToFirst())
        {
            while(cursor.moveToNext())
            {
                String check = cursor.getString(cursor.getColumnIndex("StudentName"));

                if (check.equals(name))
                {
//                    Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
                    bool = true;
                }
            }
        }
        if (bool == false)
        {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        }
        db.close();

    }
}