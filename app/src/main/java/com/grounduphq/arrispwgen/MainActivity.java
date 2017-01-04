package com.grounduphq.arrispwgen;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;

import java.util.Map;

import static com.grounduphq.arrispwgen.Constants.DEFAULT_SEED;

public class MainActivity extends AppCompatActivity {
    private LocalDate start_date;
    private LocalDate end_date;

    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        start_date = LocalDate.now();
        end_date = LocalDate.now();
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();

        update_potd_list();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_selet_dates:
                DialogFragment start_date_fragment = new DatePickerFragment();
                start_date_fragment.show(getFragmentManager(), "start_date_picker");

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void set_start_date(int year, int month, int dayOfMonth) {
        start_date = LocalDate.of(year, month, dayOfMonth);
    }

    public void set_end_date(int year, int month, int dayOfMonth) {
        end_date = LocalDate.of(year, month, dayOfMonth);
    }

    public void update_potd_list() {
        Map<LocalDate, String> potd_list = Arrispwgen.generate_multi(start_date, end_date, DEFAULT_SEED);

        TextView potd_list_view = (TextView) findViewById(R.id.potd_list);

        potd_list_view.setText(potd_list.toString());
    }
}
