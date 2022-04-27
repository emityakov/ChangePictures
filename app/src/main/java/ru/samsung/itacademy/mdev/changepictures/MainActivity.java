package ru.samsung.itacademy.mdev.changepictures;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    boolean running = false;
    Button start, stop;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        imageView = findViewById(R.id.imageView);

        Bitmap [] bitmaps = { BitmapFactory.decodeResource(getResources(), R.drawable.one),
                BitmapFactory.decodeResource(getResources(), R.drawable.two),
                BitmapFactory.decodeResource(getResources(), R.drawable.three)};

        Thread t = new Thread() {
            public void run() {
                while (running) {
                    i++;
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmaps[i % 3]);
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running) {
                    running = true;
                    t.start();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                t.interrupt();
            }
        });

    }
}