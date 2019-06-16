package alex.tomatotycoon;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ShopActivity extends AppCompatActivity {
    TextView tName1;
    TextView tName2;
    TextView tName3;
    TextView tPrice1;
    TextView tPrice2;
    TextView tPrice3;

    int[] price = {0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

       // getWindow().setLayout(width, height);
        getWindow().setLayout((int) (width*0.90),(int) (height*0.8));

        tName1 = findViewById(R.id.name1);
        tName2 = findViewById(R.id.name2);
        tName3 = findViewById(R.id.name3);
        tPrice1 = findViewById(R.id.price1);
        tPrice2 = findViewById(R.id.price2);
        tPrice3 = findViewById(R.id.price3);


        try {
            loadStore();
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

    public void loadStore() throws JSONException {
        JSONObject obj = new JSONObject(loadGameData());
        JSONArray jo = obj.getJSONArray("Upgrades");
        TextView[] namesText = {tName1, tName2, tName3};
        TextView[] priceText = {tPrice1, tPrice2, tPrice3};

        for(int i = 0; i < namesText.length; i++) {
            JSONObject upgrade = jo.getJSONObject(i);
            namesText[i].setText(String.valueOf(upgrade.getString("name")));
            priceText[i].setText(String.valueOf(upgrade.getInt("price")));
            price[i] = upgrade.getInt("price");
        }

    }


}
