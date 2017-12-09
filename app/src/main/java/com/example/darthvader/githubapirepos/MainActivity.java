package com.example.darthvader.githubapirepos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Sevices.CallbackService;
import data.Current_User;

public class  MainActivity extends AppCompatActivity  {
    EditText etUsername;
    Button btnSearch;
    TextView tvRepos;
    //private GithubService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername=(EditText)findViewById(R.id.et_usename);
        btnSearch=(Button)findViewById(R.id.btn_searchRepos);
        //tvRepos=(TextView)findViewById(R.id.tv_returnrepos);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityDisplay.class);
                intent.putExtra("username",etUsername.getText().toString());
                etUsername.setText(null);
                startActivity(intent);
                finish();

            }
        });
    }


}
