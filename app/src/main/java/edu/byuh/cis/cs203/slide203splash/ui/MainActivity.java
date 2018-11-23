package edu.byuh.cis.cs203.slide203splash.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import edu.byuh.cis.cs203.slide203splash.logic.GameMode;

/**
 * Our "main" class. Everything starts here.
 */
public class MainActivity extends Activity {

    private GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        GameMode mode = GameMode.valueOf(i.getStringExtra("GAME_MODE"));
        gv = new GameView(this, mode);
        setContentView(gv);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gv.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gv.resumeMusic();
    }

    public static float findThePerfectFontSize(float dim) {
        float fontSize = 1;
        Paint p = new Paint();
        p.setTextSize(fontSize);
        float lowerThreshold = dim;
        while (true) {
            float asc = -p.getFontMetrics().ascent;
            if (asc > lowerThreshold) {
                break;
            }
            fontSize++;
            p.setTextSize(fontSize);
        }
        return fontSize;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gv.shutdown();
    }


}