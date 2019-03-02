package com.grounduphq.arrispwgen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;

class PotdListArrayAdapter extends ArrayAdapter<Map.Entry<LocalDate, String>> {

    PotdListArrayAdapter(Context context, ArrayList<Map.Entry<LocalDate, String>> items) {
        super(context, R.layout.potd_list_item, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Map.Entry<LocalDate, String> potd = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.potd_list_item, parent, false);
        }

        // Lookup view for data population
        TextView date = convertView.findViewById(R.id.potd_date);
        TextView password = convertView.findViewById(R.id.password);

        // Populate the data into the template view using the data object
        if (potd != null) {
            date.setText(potd.getKey().toString());
            password.setText(potd.getValue());
        }

        // Return the completed view to render on screen
        return convertView;
    }


}
