package com.example.testdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

//We create a class MyDatabaseHelper and extends a parent class SQLiteOpenHelper (pre-defined class) to it
//We must also implement the two methods of the parent class
public class MyDatabaseHelper extends SQLiteOpenHelper {

    //We create string variable and assign our database name to it
    public static final String DB_NAME = "db_college";

    //We create an integer variable and assign our database version to it
    public static final int DB_VERSION = 1;

    //We create another string variable and assign it a query to create a table and we also specify
    //the two columns. First is StudentID, which will be integer, auto increment and will be primary key
    //And the second column will be StudentName which will have text data
    private static final String SQL_CREATE_STUDENT_TABLE =
            "CREATE TABLE Student (StudentID INTEGER PRIMARY KEY AUTOINCREMENT, StudentName TEXT)";

    //This is the constructor of the class
    public MyDatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //This method is overridden, it is the parent class method, we will write our code here to create our table
    @Override
    public void onCreate(SQLiteDatabase db) {

        //execSQL() method executes our query and will create the table
        db.execSQL(SQL_CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
