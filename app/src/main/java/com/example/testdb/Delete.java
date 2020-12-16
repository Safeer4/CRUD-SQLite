package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    MyDatabaseHelper dbHelper;
    SQLiteDatabase db;

    EditText uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        uName = (EditText)findViewById(R.id.name);
    }

    public void delete(View view) {
        String userName = uName.getText().toString();

        String name[] = {userName};

        dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        int rows = db.delete("Student", "StudentName=?", name);

        Toast.makeText(this, Integer.toString(rows)+" rows deleted", Toast.LENGTH_SHORT).show();
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}