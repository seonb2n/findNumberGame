package com.example.findnumbergame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RankActivity extends AppCompatActivity {

    static String name;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        //Oracle DB 로부터 데이터를 받아와서 랭킹 화면 구성

        name = showNameDialog();

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", -9999);
        Toast.makeText(this,"Score is " + score, Toast.LENGTH_LONG).show();

        //score 가 랭킹에 들면 화면에 반영
        //안들면 Toast Message 출력

    }

    private String showNameDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout nameLayout = (LinearLayout) vi.inflate(R.layout.inputdialog, null);

        final EditText editTextName = (EditText) nameLayout.findViewById(R.id.editTextName);

        new AlertDialog.Builder(this).setTitle("Input Your Name").setView(nameLayout).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = editTextName.getText().toString();
            }
                }).show();

        return name;
    }
}
