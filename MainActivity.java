package com.example.exampractical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname,mobile,address;
    Button save,view;
    TextView display;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare ids to each object variable
        uname = findViewById(R.id.userName);
        mobile = findViewById(R.id.phoneNum);
        address = findViewById(R.id.userAddress);

        save = findViewById(R.id.saveInfo);
        view = findViewById(R.id.viewInfo);

        display = findViewById(R.id.userDisplay);

        db = new DatabaseHelper(MainActivity.this, "company", null, 1);

        //save data to SQLite
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = uname.getText().toString();
                String mobNum = mobile.getText().toString();
                String userAdd = address.getText().toString();
                long recordId = db.saveNewUserData(personName,mobNum,userAdd); //calling save function
                if(recordId > 0){
                    Toast.makeText(getApplicationContext(),"SAVED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"NOT SAVED",Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String records = db.getAllRecord();
                display.setText(records);
            }
        });
    }
}