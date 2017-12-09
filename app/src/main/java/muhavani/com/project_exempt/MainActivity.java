package muhavani.com.project_exempt;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mikey on 30/11/2017.
 */

public class MainActivity extends AppCompatActivity{

    private AdView mAdview;
    ViewPager viewPager;
    ViewPager Pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isConnected();

        //ViewPager Code
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        //2nd ViewPager Code
        Pager = (ViewPager) findViewById(R.id.pager);

        pagerAdapter pageradapter = new pagerAdapter(this);
        Pager.setAdapter(pageradapter);



        //Google Ad Code
        mAdview = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //Check your LogCat to get your text device ID
                .addTestDevice("F1C43047F0F1081ECF6F4342DF39EE1E")
                .setRequestAgent("android_studio:ad_template")
                .build();
        mAdview.loadAd(adRequest);

        mAdview.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //Toast.makeText(getApplicationContext(), "Add failed to load. Error Code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }

        });
    }

        @Override
        public void onPause() {
            if (mAdview != null) {
                mAdview.pause();
            }
            super.onPause();
        }

        @Override
        public void onResume() {
            if (mAdview != null) {
                mAdview.resume();
            }
            super.onResume();
        }

        @Override
        public void onDestroy() {
            if (mAdview != null) {
                mAdview.destroy();
            }
            super.onDestroy();
        }

    //Menu Code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.about:
                //Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Tuparty App Version 1.0.0" + "\nDeveloped by: Michael Muhavani")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .setCancelable(false);
                // Create the AlertDialog object and return it
                builder.create();
                builder.show();
                break;

            case R.id.events:
                Intent intent = new Intent(MainActivity.this, listviewActivity.class);
                startActivity(intent);
                break;

            case R.id.maps:
                Intent map_intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(map_intent);
                break;
        }
        return true;
    }

    public void isConnected(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {
            //Toast.makeText(getApplicationContext(), "Please turn on your internet connection to fully utilize this app", Toast.LENGTH_LONG).show();
            connected = false;
            Intent nonet = new Intent(MainActivity.this, NoNetActivity.class);
            startActivity(nonet);
        }
    }

    public void onBackPressed(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}


