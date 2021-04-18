package com.example.findnumbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewScore;
    TextView textViewTimer;
    TextView textViewTarget;

    Button[] buttons = new Button[20];
    Button buttonStart;

    private CountDownTimer countDownTimer;
    private int MILLISINFUTURE = 30 * 1000;
    private int COUN_DONW_INTEVAL = 1000;
    private static int count = 30;

    int[] buttonNumber = new int[20];

    boolean playable = true;

    static int score;
    static int targetNumber = 0;
    static final int INTERNET_PERMISSON=1;

    Button.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScore = findViewById(R.id.textViewScore);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewTarget = findViewById(R.id.textViewTarget);

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);
        buttons[11] = findViewById(R.id.button12);
        buttons[12] = findViewById(R.id.button13);
        buttons[13]= findViewById(R.id.button14);
        buttons[14] = findViewById(R.id.button15);
        buttons[15] = findViewById(R.id.button16);
        buttons[16] = findViewById(R.id.button17);
        buttons[17] = findViewById(R.id.button18);
        buttons[18] = findViewById(R.id.button19);
        buttons[19] = findViewById(R.id.button20);

        buttonStart = findViewById(R.id.buttonStart);

        for(int i = 0; i<20; i++) {
            buttonNumber[i] = i+1;
            buttons[i].setBackgroundColor(0xFFDADADA);
        }

        shuffle(buttonNumber);
        setButtonNumber(buttonNumber);

        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                gameReset();
                targetNumber = (int)(Math.random() * 20) + 1;
                textViewTarget.setText(String.valueOf(targetNumber));
                gameStart();
            }
        });

        int permissonCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "인터넷 접속 권한 있음", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "인터넷 접속 권한 있음", Toast.LENGTH_SHORT).show();

            //권한설정 dialog에서 거부를 누르면
            //ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다.
            //단, 사용자가 "Don't ask again"을 체크한 경우
            //거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
                //이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "인터넷 접속 권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.INTERNET}, INTERNET_PERMISSON);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS}, INTERNET_PERMISSON);
            }
        }

        clickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button1:
                        targetNumber = gameCycle(targetNumber, buttons[0]);
                        break;
                    case R.id.button2:
                        targetNumber = gameCycle(targetNumber, buttons[1]);
                        break;
                    case R.id.button3:
                        targetNumber = gameCycle(targetNumber, buttons[2]);
                        break;
                    case R.id.button4:
                        targetNumber = gameCycle(targetNumber, buttons[3]);
                        break;
                    case R.id.button5:
                        targetNumber = gameCycle(targetNumber, buttons[4]);
                        break;
                    case R.id.button6:
                        targetNumber = gameCycle(targetNumber, buttons[5]);
                        break;
                    case R.id.button7:
                        targetNumber = gameCycle(targetNumber, buttons[6]);
                        break;
                    case R.id.button8:
                        targetNumber = gameCycle(targetNumber, buttons[7]);
                        break;
                    case R.id.button9:
                        targetNumber = gameCycle(targetNumber, buttons[8]);
                        break;
                    case R.id.button10:
                        targetNumber = gameCycle(targetNumber, buttons[9]);
                        break;
                    case R.id.button11:
                        targetNumber = gameCycle(targetNumber, buttons[10]);
                        break;
                    case R.id.button12:
                        targetNumber = gameCycle(targetNumber, buttons[11]);
                        break;
                    case R.id.button13:
                        targetNumber = gameCycle(targetNumber, buttons[12]);
                        break;
                    case R.id.button14:
                        targetNumber = gameCycle(targetNumber, buttons[13]);
                        break;
                    case R.id.button15:
                        targetNumber = gameCycle(targetNumber, buttons[14]);
                        break;
                    case R.id.button16:
                        targetNumber = gameCycle(targetNumber, buttons[15]);
                        break;
                    case R.id.button17:
                        targetNumber = gameCycle(targetNumber, buttons[16]);
                        break;
                    case R.id.button18:
                        targetNumber = gameCycle(targetNumber, buttons[17]);
                        break;
                    case R.id.button19:
                        targetNumber = gameCycle(targetNumber, buttons[18]);
                        break;
                    case R.id.button20:
                        targetNumber = gameCycle(targetNumber, buttons[19]);
                        break;
                }
            }
        };

        for(int i = 0; i < 20; i++) {
            buttons[i].setOnClickListener(clickListener);
        }

    }

    public void gameStart() {

        countDownTimer();
        countDownTimer.start();

    }

    public void countDownTimer() {
        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUN_DONW_INTEVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(String.valueOf(count));
                count--;
            }
            @Override
            public void onFinish() {
                textViewTimer.setText(String.valueOf("Finish"));

                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
                myAlertBuilder.setTitle("Score is " + score);
                myAlertBuilder.setMessage("Click RANK To Rank, or Cancel to stop");
                myAlertBuilder.setPositiveButton("RANK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getApplicationContext(), RankActivity.class);
                        intent.putExtra("Score", score);
                        startActivity(intent);

                        gameReset();
                        Toast.makeText(getApplicationContext(), "pressed Rank", Toast.LENGTH_SHORT).show();

                    }
                });

                myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();
                    }
                });
                myAlertBuilder.show();
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            countDownTimer.cancel();
        } catch (Exception e) {}
        countDownTimer = null;
    }

    public int[] shuffle(int[] arr) {
        for(int x = 0; x<arr.length; x++) {
            int i = (int)(Math.random() * arr.length);
            int j = (int)(Math.random() * arr.length);

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        return arr;
    }

    public void setButtonNumber(int[] array) {
        for(int i = 0; i < 20; i++) {
            buttons[i].setText(String.valueOf(array[i]));
        }
    }

    public int gameCycle(int targetNumber, Button button) {
        String buttonText = button.getText().toString();
        int target = targetNumber;
        if(Integer.parseInt(buttonText) == target) {
            score +=1;
            textViewScore.setText(String.valueOf(score));
            target = (int)(Math.random() * 20) + 1;
            textViewTarget.setText(String.valueOf(target));
            shuffle(buttonNumber);
            setButtonNumber(buttonNumber);

            return target;

        }else {
            button.setBackgroundColor(0xFFFF0000);
            return targetNumber;
        }
    }

    public void gameReset() {
        textViewTimer.setText(String.valueOf(30));
        count = 30;
        score = 0;
        textViewScore.setText(String.valueOf(score));

        for(int i = 0; i<20; i++) {
            buttons[i].setBackgroundColor(0xFFDADADA);
        }

    }

}