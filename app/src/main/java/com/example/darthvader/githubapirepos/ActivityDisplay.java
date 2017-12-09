package com.example.darthvader.githubapirepos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import Sevices.CallbackService;
import Sevices.GithubService;
import data.Current_User;

/**
 * Created by darthvader on 9/12/17.
 */

public class ActivityDisplay extends AppCompatActivity implements CallbackService {

    private GithubService service;
    TextView tvRepos;
    Button btnDone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        service=new GithubService(this);
        tvRepos=(TextView)findViewById(R.id.tv_repos);
        btnDone=(Button)findViewById(R.id.btn_done);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        Log.d("Hello",username);
        service.refreshRepos(username);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ActivityDisplay.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
    @Override
    public void serviceSuccess(Current_User user) {
        //dialog.hide();
        tvRepos.setText(user.getNames());

    }

    @Override
    public void serviceFailure(Exception e) {
        //dialog.hide();
        tvRepos.setText(null);
        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void repoNotFound(Exception e) {
        tvRepos.setText(null);
        Toast.makeText(getApplicationContext(), "Repositories not found", Toast.LENGTH_SHORT).show();
    }
}
