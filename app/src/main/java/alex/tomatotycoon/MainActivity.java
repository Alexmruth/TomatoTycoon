package alex.tomatotycoon;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView tomatoImg;
    TextView countText;
    Button shopBtn;
    int multiplier = 1;
    int tomatoes = 0;

    // The Image uses 88% of the screen width
    private static final float IMAGE_SCREEN_WIDTH_RATIO = 0.60f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tomatoImg = findViewById(R.id.tomato);
        countText = findViewById(R.id.countText);

        shopBtn = findViewById(R.id.shopBtn);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;;
        int imageViewSize = (int) ((float) width * IMAGE_SCREEN_WIDTH_RATIO);
        int margin = (width - imageViewSize) / 2;
        ViewGroup.LayoutParams params = tomatoImg.getLayoutParams();
        params.height = imageViewSize;
        params.width = imageViewSize;

        tomatoImg.requestLayout();

    }

    public void onTomatoClick(View v) {
        tomatoes = tomatoes + (1*multiplier);
        countText.setText(String.valueOf(tomatoes));
    }

    public void shopBtnClick(View view) {
        startActivity(new Intent(MainActivity.this,ShopActivity.class));
    }
}
