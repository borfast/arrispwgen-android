package com.grounduphq.arrispwgen;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import static com.grounduphq.arrispwgen.Constants.DEFAULT_SEED;

public class MainActivity extends AppCompatActivity implements SetSeedDialogFragment.SetSeedDialogListener {
    private LocalDate start_date;
    private LocalDate end_date;
    private String seed;
    private ListView potd_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        start_date = LocalDate.now();
        end_date = LocalDate.now();
        seed = DEFAULT_SEED;
        setContentView(R.layout.activity_main);

        // Set up consent for Android Advertising ID, showing a consent dialog if necessary.
        AdView adView = findViewById(R.id.adView);
        AdsConsent adsConsent = new AdsConsent(this, adView);
        ConsentInformation consentInformation = adsConsent.initialise();

        // Load an ad into the AdMob banner view.

        AdRequest.Builder adRequestBuilder = new AdRequest.Builder().setRequestAgent("android_studio:ad_template");
        // Use non-personalised ads if the user so chose
        if (consentInformation.getConsentStatus() == ConsentStatus.NON_PERSONALIZED) {
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            adRequestBuilder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
        }
        AdRequest adRequest = adRequestBuilder.build();
        adView.loadAd(adRequest);

        // Allow long-clicking to copy the password
        potd_list_view = findViewById(R.id.potd_list);
        potd_list_view.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView potd_view = view.findViewById(R.id.password);
                String potd = potd_view.getText().toString();

                Context ctx = view.getContext();
                ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("ARRISPWGENPOTD", potd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(ctx, "Password copied to clipboard.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        generate_potd_list();
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

            case R.id.action_set_seed:
                DialogFragment set_seed_fragment = new SetSeedDialogFragment();

                Bundle args = new Bundle();
                args.putString("seed", seed);
                set_seed_fragment.setArguments(args);

                set_seed_fragment.show(getFragmentManager(), "set_seed_dialog");
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

    public void generate_potd_list() {
        (new ArrispwgenTask(this)).execute();
    }

    private void update_potd_list_view(Map<LocalDate, String> potd_list) {
        ArrayList<Map.Entry<LocalDate, String>> list = new ArrayList<>(potd_list.entrySet());
        PotdListArrayAdapter adapter = new PotdListArrayAdapter(this, list);
        adapter.sort(new Comparator<Map.Entry<LocalDate, String>>() {
            @Override
            public int compare(Map.Entry<LocalDate, String> o1, Map.Entry<LocalDate, String> o2) {
                return o1.getKey().compareTo(o2.getKey()); //isAfter(o2.getKey());
            }
        });
        potd_list_view.setAdapter(adapter);
    }

    // User pressed OK
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogView = dialog.getDialog();
        EditText txt_seed = dialogView.findViewById(R.id.txt_seed);
        seed = txt_seed.getText().toString();
        generate_potd_list();
    }

    // User pressed Cancel
    @Override
    public void onDialogNegativeClick() {

    }

    // User pressed default
    @Override
    public void onDialogNeutralClick() {
        seed = DEFAULT_SEED;
        generate_potd_list();
    }

    private static class ArrispwgenTask extends AsyncTask<Void, Void, Map<LocalDate, String>> {
        private WeakReference<MainActivity> activityReference;
        private LocalDate start_date;
        private LocalDate end_date;
        private String seed;

        // only retain a weak reference to the activity
        private ArrispwgenTask(MainActivity activityReference) {
            this.activityReference = new WeakReference<>(activityReference);
            this.start_date = activityReference.start_date;
            this.end_date = activityReference.end_date;
            this.seed = activityReference.seed;
        }

        /* The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected Map<LocalDate, String> doInBackground(Void... params) {
            return Arrispwgen.generate_multi(this.start_date, this.end_date, this.seed);
        }

        /* The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(Map<LocalDate, String> potd_list) {
            // get a reference to the activity if it is still there
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.update_potd_list_view(potd_list);
        }
    }
}
