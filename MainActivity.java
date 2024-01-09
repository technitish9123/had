package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname,pass;
    TextView txt;
    Button ins,del,dis,upd;
    dbhelper helper;
    private static final String dbname="comp";
    private static final String tbname = "employ";
    private static final int dbversion =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.pass);
        txt=findViewById(R.id.txt);
    }

    public void insert(View view) {
        helper=new dbhelper(MainActivity.this,dbname,null,dbversion);
        long lo=helper.adduser(uname.getText().toString(),pass.getText().toString());
        if(lo==-1)
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        helper=new dbhelper(MainActivity.this,dbname,null,dbversion);
        helper.delete(uname.getText().toString());
    }

    public void update(View view) {
        helper=new dbhelper(MainActivity.this,dbname,null,dbversion);
        helper.update(uname.getText().toString(),pass.getText().toString());
    }

    public void display(View view) {
        helper=new dbhelper(MainActivity.this,dbname,null,dbversion);
        String res= helper.display(MainActivity.this);
        txt.setText(res);
    }
}