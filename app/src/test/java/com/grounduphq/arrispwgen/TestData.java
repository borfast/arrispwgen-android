package com.grounduphq.arrispwgen;

import org.threeten.bp.LocalDate;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

final class TestData {
    private static Calendar cal = Calendar.getInstance();
    static final String custom_seed = "ABCDEFGHIJ";
    public static final Map<LocalDate, String> using_default_seed = new LinkedHashMap<LocalDate, String>();
    public static final Map<LocalDate, String> using_custom_seed = new LinkedHashMap<LocalDate, String>();

    static {
        cal.set(2016, 10, 19);
        using_default_seed.put(LocalDate.of(2016, 10, 19), "2BEIRWLZ35");
        using_default_seed.put(LocalDate.of(2016, 10, 20), "O2A2MLL32D");
        using_default_seed.put(LocalDate.of(2016, 10, 21), "K716USYKFR");
        using_default_seed.put(LocalDate.of(2016, 10, 22), "NUD21IN1EV");
        using_default_seed.put(LocalDate.of(2016, 11, 1), "1F3I94R4FO");
        using_default_seed.put(LocalDate.of(2016, 11, 2), "64Y3MJRL0N");
        using_default_seed.put(LocalDate.of(2016, 11, 3), "DTARW8TPKM");
    }

    static {
        using_custom_seed.put(LocalDate.of(2016, 10, 19), "KF6WGPG74Q");
        using_custom_seed.put(LocalDate.of(2016, 10, 20), "2DL07E57EK");
        using_custom_seed.put(LocalDate.of(2016, 10, 21), "E93CGHF957");
        using_custom_seed.put(LocalDate.of(2016, 10, 22), "EG304636TM");
        using_custom_seed.put(LocalDate.of(2016, 11, 1), "9KEWMOYQ82");
        using_custom_seed.put(LocalDate.of(2016, 11, 2), "IBV5K74ER9");
        using_custom_seed.put(LocalDate.of(2016, 11, 3), "ELEXOJ6B2Q");
    }
}
