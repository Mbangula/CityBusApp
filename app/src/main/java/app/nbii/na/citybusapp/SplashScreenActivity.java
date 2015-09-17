package app.nbii.na.citybusapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class SplashScreenActivity extends ActionBarActivity {

    private static final String TAG=SplashScreenActivity.class.getSimpleName();

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private Bundle savedInstanceState;

    private void HideActionBar(){
        //hiding the support action bar
        getSupportActionBar().hide();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide the support action bar
        try {
            HideActionBar();
        } catch(Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}