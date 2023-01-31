package com.example.kolory;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int wynik = 0;

    String[] kolory = {
            "Czarny",
            "Czerwony",
            "Niebieski",
            "Żółty",
            "Fioletowy",
            "Pomarańczowy",
            "Brązowy",
            "Bordowy",
            "Morski",
            "Szkarłatny",
            "Indigo",
            "Różowy",
            "Beżowy",
            "Mahoniowy",
            "Bursztynowy",
            "Fiołkowy",
            "Khaki",
            "Lazurowy",
            "Magenta",
            "Oliwkowy",
            "Perłowy",
            "Purpurowy",
            "Turkusowy",
            "Złoty",
            "Szary",
            "Rudy",
            "Marchewkowy",
            "Miętowy",
            "Szmaragowy",
            "Błękitny",
    };

    int[] koloryPng = {
            R.drawable.czarny,
            R.drawable.czerw,
            R.drawable.niebieski,
            R.drawable.zolty,
            R.drawable.fioletowy,
            R.drawable.pom,
            R.drawable.braz,
            R.drawable.bord,
            R.drawable.morski2,
            R.drawable.szkarlat,
            R.drawable.indygo,
            R.drawable.rozowy,
            R.drawable.bezowy,
            R.drawable.mahon,
            R.drawable.bursztyn,
            R.drawable.fiolek,
            R.drawable.khaki,
            R.drawable.lazur,
            R.drawable.magenta2,
            R.drawable.oliwka,
            R.drawable.perlowy,
            R.drawable.purpury,
            R.drawable.turkus2,
            R.drawable.zloty,
            R.drawable.szary,
            R.drawable.rudy,
            R.drawable.marchewkowy,
            R.drawable.mietowy,
            R.drawable.szmaragowy,
            R.drawable.blekitny,

    };

    Button start;
    TextView viewWynik;
    TextView czas;
    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    TextView kolor;
    Button restart;
    TextView komunikat;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;

    int [] dzwieki = {
            R.raw.blad,
            R.raw.dobrze,
            R.raw.end

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        start = findViewById(R.id.start);
        czas = findViewById(R.id.czas);
        viewWynik = findViewById(R.id.viewWynik);
        kolor = findViewById(R.id.kolor);
        komunikat = findViewById(R.id.komunikat);
        restart = findViewById(R.id.restart);
        Restart(findViewById(R.id.czas));

    }

    public void start(View view) {
        start.setVisibility(View.INVISIBLE);



    }



    public void newQuestion() {
        Random random = new Random();
        int a = random.nextInt(kolory.length);



        kolor.setText(kolory[a]);


        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a);
            } else {

                int wrongAnswer = random.nextInt(kolory.length);
                while (wrongAnswer == a ) {
                    wrongAnswer = random.nextInt(kolory.length);
                }

                answers.add(wrongAnswer);
            }

        }
        button1.setImageResource(koloryPng[answers.get(0)]);
        button2.setImageResource(koloryPng[answers.get(1)]);
        button3.setImageResource(koloryPng[answers.get(2)]);
        button4.setImageResource(koloryPng[answers.get(3)]);
    }
    public void odpowiedz(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            wynik++;
            komunikat.setText("Dobrze!");
            mediaPlayer2 = MediaPlayer.create(MainActivity.this, dzwieki[1]);
            mediaPlayer2.start();


        } else {
            komunikat.setText("Zle");
            wynik--;
            mediaPlayer1 = MediaPlayer.create(MainActivity.this, dzwieki[0]);
            mediaPlayer1.start();



        }
        viewWynik.setText(Integer.toString(wynik));
        newQuestion();
    }

    public void Restart(View view) {
        czas.setText("30s");
        viewWynik.setText("0");
        komunikat.setText("");
        restart.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        wynik = 0;

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                czas.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                komunikat.animate().rotation(1080).alpha(1).setDuration(1000);
                komunikat.setText("Koniec czasu. Twój wynik to:" + " " + Integer.toString(wynik) + "pkt" );
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.VISIBLE);
                mediaPlayer3 = MediaPlayer.create(MainActivity.this, dzwieki[2]);
                mediaPlayer3.start();

            }
        }
        .start();
        newQuestion();

    }
}