package alex.tomatotycoon;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton tomatoImg, rainTest;
    ImageView littleTomato;
    TextView countText;
    Button shopBtn;
    ProgressBar rainProgress;
    int multiplier = 1;
    int tomatoes = 0;
    int rainProgressInt = 0;

    // The Image uses 88% of the screen width
    private static final float IMAGE_SCREEN_WIDTH_RATIO = 0.60f;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tomatoImg = findViewById(R.id.tomato);
        littleTomato = findViewById(R.id.littleTomato);
        countText = findViewById(R.id.countText);

        rainTest = findViewById(R.id.rainTest);

        rainProgress = findViewById(R.id.rainProgress);
        rainProgress.setMax(100);
        shopBtn = findViewById(R.id.shopBtn);


        tomatoImg.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                ViewGroup.LayoutParams params = tomatoImg.getLayoutParams();
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    tomatoes = tomatoes + (1*multiplier);
                    rainProgressInt = rainProgressInt + 1;
                    countText.setText(String.valueOf(tomatoes));
                    rainProgress.setProgress(rainProgressInt);

                    if(rainProgressInt >= 100) {
                        tomatoRain();
                        rainProgressInt = 0;
                        rainProgress.setProgress(rainProgressInt);
                    }
                    params.height = params.height - 30;
                    params.width = params.width - 30;
                    view.setLayoutParams(params);
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    params.height = params.height + 30;
                    params.width = params.width + 30;
                    view.setLayoutParams(params);
                }
                return true;
            }
        });
    }

    public void tomatoRain() {

    }


    public void shopBtnClick(View view) {
        startActivity(new Intent(MainActivity.this,ShopActivity.class));
    }
}
