package com.example.daynightswitch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchAnimListener;
import com.mahfa.dnswitch.DayNightSwitchListener;

public class MainActivity extends AppCompatActivity {

    View day_bg,night_bg,sun;
    ImageView night_img,day_img;
    DayNightSwitch dayNightSwitch;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color_day));

        day_bg=findViewById(R.id.day_bg);
        night_bg=findViewById(R.id.night_bg);
        sun=findViewById(R.id.sun);
        night_img=findViewById(R.id.night_img);
        day_img=findViewById(R.id.day_img);
        dayNightSwitch=findViewById(R.id.day_night_switch);

        sun.setTranslationY(-90);
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                if(is_night)
                {
                    window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.my_statusbar_color_night));
                    sun.animate().translationY(220).setDuration(1300);
                    day_bg.animate().alpha(0).setDuration(1300);
                    day_img.animate().alpha(0).setDuration(1300);
                }
                else
                {
                    window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.my_statusbar_color_day));
                    sun.animate().translationY(-90).setDuration(1300);
                    day_img.animate().alpha(1).setDuration(1300);
                    day_bg.animate().alpha(1).setDuration(1300);
                }
            }
        });
    }
}