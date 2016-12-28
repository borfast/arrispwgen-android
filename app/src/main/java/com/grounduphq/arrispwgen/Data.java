package com.grounduphq.arrispwgen;

import org.threeten.bp.LocalDate;

import static com.grounduphq.arrispwgen.Constants.TABLE1;
import static com.grounduphq.arrispwgen.Constants.TABLE2;

class Data {
    private static int[] list1(LocalDate date) {
        // Last two digits of the year
        int year = Integer.parseInt(Integer.toString(date.getYear()).substring(1, 3));

        // Number of the month. Contrary to the Javascript version, the month indices here
        // start at 1, i.e., January == 1, so we don't need to add 1 to satisfy the algorithm.
        int month = date.getMonthValue();

        int day_of_month = date.getDayOfMonth();

        // Day of the week. From 1 (Monday) to 7 (Sunday) but we need 0 to be Monday.
        int day_of_week = date.getDayOfWeek().getValue() - 1;

        int[] list1 = new int[8];

        System.arraycopy(TABLE1[day_of_week], 0, list1, 0, 5);

        list1[5] = day_of_month;

        if (((year + month) - day_of_month) < 0) {
            list1[6] = (((year + month) - day_of_month) + 36) % 36;
        } else {
            list1[6] = ((year + month) - day_of_month) % 36;
        }

        list1[7] = (((3 + ((year + month) % 12)) * day_of_month) % 37) % 36;

        return list1;
    }

    private static int[] list2(String seed) {
        int[] list2 = new int[8];

        for (int i = 0; i <= 7; i++) {
            list2[i] = ((int) seed.charAt(i)) % 36;
        }

        return list2;
    }

    private static int num8(int[] l3) {
        return l3[8] % 6;
    }

    private static int[] list3(int[] l1, int[] l2) {
        int[] list3 = new int[10];

        for (int i = 0; i <= 7; i++) {
            list3[i] = (((l1[i] + l2[i])) % 36);
        }

        list3[8] = (list3[0] + list3[1] + list3[2] + list3[3] + list3[4] +
                list3[5] + list3[6] + list3[7]) % 36;

        int n = num8(list3);
        list3[9] = (int) Math.round(Math.pow(n, 2));

        return list3;
    }

    private static int[] list4(int[] l3) {
        int[] list4 = new int[10];

        for (int i = 0; i <= 9; i++) {
            list4[i] = l3[TABLE2[num8(l3)][i]];
        }

        return list4;
    }

    private static int[] list5(String seed, int[] l4) {
        int[] list5 = new int[10];

        for (int i = 0; i <= 9; i++) {
            list5[i] = (((int) seed.charAt(i)) + l4[i]) % 36;
        }

        return list5;
    }

    static int[] indexers(LocalDate date, String seed) {
        int[] l1 = list1(date);
        int[] l2 = list2(seed);
        int[] l3 = list3(l1, l2);
        int[] l4 = list4(l3);

        return list5(seed, l4);
    }
}
