package com.grounduphq.arrispwgen;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import org.threeten.bp.LocalDate;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String tag = getTag();

        MainActivity act = (MainActivity) getActivity();

        switch (tag) {
            case "start_date_picker":
                act.set_start_date(year, month, dayOfMonth);
                DialogFragment end_date_fragment = new DatePickerFragment();
                end_date_fragment.show(getFragmentManager(), "end_date_picker");
                break;
            case "end_date_picker":
                act.set_end_date(year, month, dayOfMonth);
                act.update_potd_list();
                break;
        }
    }

}
