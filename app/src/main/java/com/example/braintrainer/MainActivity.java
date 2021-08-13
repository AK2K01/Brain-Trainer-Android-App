package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView finalscreen;
    TextView scoretext;
    Button playagain;
    TextView timertext;
    TextView resulttext;
    CountDownTimer timer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers;
    Button gobutton;
    TextView questiontext;
    String[] ops={"+","-","X","/"};
    int correctloc;
    int score=0;
    int noofq=0;
    public void choice(View view)
    {
        String option=view.getTag().toString();
        if(option.equals(Integer.toString(correctloc)))
        {
            resulttext.setVisibility(View.VISIBLE);
            resulttext.setText("Correct!");
            score++;
        }
        else
        {
            resulttext.setVisibility(View.VISIBLE);
            resulttext.setText("Incorrect!");
        }
        noofq++;
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(noofq));
        answers.clear();
        newquestion();
    }
    public void newquestion()
    {
        Random rand=new Random();
        int a=rand.nextInt(41);
        int b=rand.nextInt(41);
        String op=ops[rand.nextInt(ops.length)];
        questiontext.setText(Integer.toString(a)+op+Integer.toString(b));
        int range1,range2;
        int result;
        if(op.equals("+"))
        {
            range1=0;
            range2=81;
            result=a+b;
        }
        else if(op.equals("-"))
        {
            range1=-40;
            range2=41;
            result=a-b;
        }
        else if(op.equals("X"))
        {
            range1=0;
            range2=1601;
            result=a*b;
        }
        else{
            range1=0;
            range2=41;
            result=Math.round(a/b);
        }
        correctloc=rand.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i==correctloc)
            {
                answers.add(result);
            }
            else
            {
                int random=rand.nextInt(range2)+range1;
                while(random==result)
                {
                    random=rand.nextInt(range2)+range1;
                }
                answers.add(random);
            }
        }
        button0.setText(answers.get(0).toString());
        button1.setText(answers.get(1).toString());
        button2.setText(answers.get(2).toString());
        button3.setText(answers.get(3).toString());
    }
    public void startgame(View view)
    {
        gobutton.setVisibility(View.INVISIBLE);
        timertext.setVisibility(View.VISIBLE);
        questiontext.setVisibility(View.VISIBLE);
        scoretext.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        starttimer();
    }
    public void restart(View view)
    {
        timertext.setVisibility(View.VISIBLE);
        questiontext.setVisibility(View.VISIBLE);
        scoretext.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        finalscreen.setVisibility(View.INVISIBLE);
        answers.clear();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score=0;
        noofq=0;
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(noofq));
        newquestion();
        starttimer();
        playagain.setVisibility(View.INVISIBLE);
        resulttext.setVisibility(View.INVISIBLE);
    }
    public void starttimer()
    {
        timer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                int tl=(int)(l/1000);
                timertext.setText(Integer.toString(tl)+"s");
            }

            @Override
            public void onFinish() {
                resulttext.setText("Done!");
                playagain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                timertext.setVisibility(View.INVISIBLE);
                questiontext.setVisibility(View.INVISIBLE);
                scoretext.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                finalscreen.setVisibility(View.VISIBLE);
                finalscreen.setText("Your Score:\n"+Integer.toString(score)+"/"+Integer.toString(noofq));
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finalscreen=(TextView)findViewById(R.id.finalscreen);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        gobutton=(Button)findViewById(R.id.gobutton);
        questiontext=(TextView)findViewById(R.id.questiontext);
        answers=new ArrayList<Integer>();
        playagain=(Button)findViewById(R.id.playagain);
        timertext=(TextView)findViewById(R.id.timertext);
        resulttext=(TextView)findViewById(R.id.resulttext);
        scoretext=(TextView)findViewById(R.id.scoretext);
        newquestion();
    }
}