package com.example.findnumbergame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RankActivity extends AppCompatActivity {

    static String name;
    static String rankData = null;
    TextView[] textViewNames = new TextView[5];
    TextView[] textViewScores = new TextView[5];



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        textViewNames[0] = findViewById(R.id.textViewNameOne);
        textViewNames[1] = findViewById(R.id.textViewNameTwo);
        textViewNames[2] = findViewById(R.id.textViewNameThree);
        textViewNames[3] = findViewById(R.id.textViewNameFour);
        textViewNames[4] = findViewById(R.id.textViewNameFive);

        textViewScores[0] = findViewById(R.id.textViewScoreOne);
        textViewScores[1] = findViewById(R.id.textViewScoreTwo);
        textViewScores[2] = findViewById(R.id.textViewScoreThree);
        textViewScores[3] = findViewById(R.id.textViewScoreFour);
        textViewScores[4] = findViewById(R.id.textViewScoreFive);


        String sendMsg = "update_rank";
        String result  = null;
        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", -9999);
        rankGetter(rankData, score);
        name = showNameDialog(score);

        //score 가 랭킹에 들면 화면에 반영
        //안들면 Toast Message 출력

    }

    private String showNameDialog(final int score) {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout nameLayout = (LinearLayout) vi.inflate(R.layout.inputdialog, null);

        final EditText editTextName = (EditText) nameLayout.findViewById(R.id.editTextName);

        new AlertDialog.Builder(this).setTitle("Input Your Name").setView(nameLayout).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = editTextName.getText().toString();
                rankUpdater(rankData, score);
            }
                }).show();
        return name;
    }

    private void rankUpdater(String rankData, int score) {

        try {
            String result = new Task("update_rank").execute(name, String.valueOf(score)).get();
            Log.i("Return ",result);
            rankData = result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(rankData != null) {
            String str= rankData;
            String[] array = str.split("@");
            String[] rankerName = new String[6];
            String[] rankerScore = new String [6];
            for(int i = 0; i<array.length; i++){
                rankerName[i] = array[i].substring(0, array[i].indexOf("#"));
                rankerScore[i] = array[i].substring(array[i].lastIndexOf("#")+1);
            }

            for(int i = 0; i < 5; i++) {
                textViewNames[i].setText(rankerName[i]);
                textViewScores[i].setText(rankerScore[i]);
            }
        }

    }

    private void rankGetter(String rankData, int score) {
        try {
            String result = new Task("get_rank").execute(name, String.valueOf(score)).get();
            rankData = result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(rankData != null) {
            String str= rankData;
            String[] array = str.split("@");
            String[] rankerName = new String[6];
            String[] rankerScore = new String [6];
            for(int i = 0; i<array.length; i++){
                rankerName[i] = array[i].substring(0, array[i].indexOf("#"));
                rankerScore[i] = array[i].substring(array[i].lastIndexOf("#")+1);
            }

            for(int i = 0; i < 5; i++) {
                textViewNames[i].setText(rankerName[i]);
                textViewScores[i].setText(rankerScore[i]);
            }
        }
    }
}
