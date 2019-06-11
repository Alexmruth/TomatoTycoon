package alex.tomatotycoon;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

       // getWindow().setLayout(width, height);
        getWindow().setLayout((int) (width*0.8),(int) (height*0.8));

        try {
            loadPlayerData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadGameData() {
        String json = null;
        try {
            InputStream file = getAssets().open("gameData.json");
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            json = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void loadPlayerData() throws JSONException {
        JSONObject obj = new JSONObject(loadGameData());
        JSONObject jo = obj.getJSONObject("Upgrades");
        int base = jo.getInt("baseAttMin");
        int baseAttMax = jo.getInt("baseAttMax");
    }

}
