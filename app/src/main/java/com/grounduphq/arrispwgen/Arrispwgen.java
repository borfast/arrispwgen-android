package com.grounduphq.arrispwgen;


import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.HashMap;
import java.util.Map;

import static com.grounduphq.arrispwgen.Constants.ALPHANUM;

class Arrispwgen {
    static String generate(LocalDate date, String seed) {
        int[] idx = Data.indexers(date, seed);
        int len = idx.length;

        char[] password_of_the_day = new char[len];

        for (int i = 0; i < len; i++) {
            password_of_the_day[i] = ALPHANUM[idx[i]];
        }

        return new String(password_of_the_day);
    }

    static Map<LocalDate, String> generate_multi(LocalDate start_date, LocalDate end_date, String seed) {
        if (start_date.isAfter(end_date)) {
            throw new IllegalArgumentException();
        }

        int days = (int) (1 + ChronoUnit.DAYS.between(start_date, end_date));

        // Now let's generate one password for each day
        Map<LocalDate, String> password_list = new HashMap<>();
        LocalDate date = start_date;
        for (int i = 0; i < days; i++) {
            password_list.put(date, generate(date, seed));
            date = date.plusDays(1);
        }

        return password_list;
    }
}
