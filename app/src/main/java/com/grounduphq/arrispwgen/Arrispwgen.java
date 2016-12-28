package com.grounduphq.arrispwgen;


import org.threeten.bp.LocalDate;

import static com.grounduphq.arrispwgen.Constants.ALPHANUM;

class Arrispwgen {
    String generate(LocalDate date, String seed) {
        int[] idx = Data.indexers(date, seed);
        int len = idx.length;

        char[] password_of_the_day = new char[len];

        for (int i = 0; i < len; i++) {
            password_of_the_day[i] = ALPHANUM[idx[i]];
        }

        return new String(password_of_the_day);
    }
}
