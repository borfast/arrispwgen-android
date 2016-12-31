package com.grounduphq.arrispwgen;

import org.threeten.bp.LocalDate;

import java.util.HashMap;
import java.util.Map;

final class DataHelper {
    static final String custom_seed = "ABCDEFGHIJ";

    public static final LocalDate[] TEST_DATES = {
            LocalDate.of(2016, 10, 19),
            LocalDate.of(2016, 10, 20),
            LocalDate.of(2016, 10, 21),
            LocalDate.of(2016, 10, 22),
            LocalDate.of(2016, 11, 1),
            LocalDate.of(2016, 11, 2),
            LocalDate.of(2016, 11, 3)
    };

    public static final Map<LocalDate, String> using_default_seed = new HashMap<>();
    public static final Map<LocalDate, String> using_custom_seed = new HashMap<>();

    static {
        using_default_seed.put(TEST_DATES[0], "2BEIRWLZ35");
        using_default_seed.put(TEST_DATES[1], "O2A2MLL32D");
        using_default_seed.put(TEST_DATES[2], "K716USYKFR");
        using_default_seed.put(TEST_DATES[3], "NUD21IN1EV");
        using_default_seed.put(TEST_DATES[4], "1F3I94R4FO");
        using_default_seed.put(TEST_DATES[5], "64Y3MJRL0N");
        using_default_seed.put(TEST_DATES[6], "DTARW8TPKM");
    }

    static {
        using_custom_seed.put(TEST_DATES[0], "KF6WGPG74Q");
        using_custom_seed.put(TEST_DATES[1], "2DL07E57EK");
        using_custom_seed.put(TEST_DATES[2], "E93CGHF957");
        using_custom_seed.put(TEST_DATES[3], "EG304636TM");
        using_custom_seed.put(TEST_DATES[4], "9KEWMOYQ82");
        using_custom_seed.put(TEST_DATES[5], "IBV5K74ER9");
        using_custom_seed.put(TEST_DATES[6], "ELEXOJ6B2Q");
    }

    /**
     * list 1 is always the same for both default and custom seeds
     * because it only depends on the date.
     */
    private static final int[][] l1 = {
        {34, 27, 16, 23, 30, 19, 8, 3},
        {14, 22, 24, 17, 13, 20, 7, 9},
        {15, 15, 24, 20, 24, 21, 6, 15},
        {13, 14, 27, 32, 10, 22, 5, 21},
        {23, 32, 24, 29, 29, 1, 27, 7},
        {14, 29, 10, 21, 29, 2, 26, 14},
        {34, 27, 16, 23, 30, 3, 25, 21}
    };

    public static final Map<LocalDate, int[]> test_list1 = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_list1.put(TEST_DATES[i], l1[i]);
        }
    }


    /**
     * Data for default seed lists.
     */

    public static final int[] test_list2_using_default_seed = {5, 8, 11, 2, 3, 5, 32, 0};

    private static final int[][] l3_default = {
        {3, 35, 27, 25, 33, 24, 4, 3, 10, 16 },
        {19, 30, 35, 19, 16, 25, 3, 9, 12, 0 },
        {20, 23, 35, 22, 27, 26, 2, 15, 26, 4 },
        {18, 22, 2, 34, 13, 27, 1, 21, 30, 0 },
        {28, 4, 35, 31, 32, 6, 23, 7, 22, 16 },
        {19, 1, 21, 23, 32, 7, 22, 14, 31, 1 },
        {3, 35, 27, 25, 33, 8, 21, 21, 29, 25 },
    };
    public static final Map<LocalDate, int[]> test_list3_using_default_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
        test_list3_using_default_seed.put(TEST_DATES[i], l3_default[i]);
        }
    }

    private static final int[][] l4_default = {
        {33, 3, 3, 16, 24, 27, 25, 35, 10, 4 },
        {19, 30, 35, 0, 19, 16, 25, 3, 9, 12 },
        {15, 35, 26, 4, 27, 23, 2, 20, 22, 26 },
        {18, 22, 2, 0, 34, 13, 27, 1, 21, 30 },
        {32, 7, 28, 16, 6, 35, 31, 4, 22, 23 },
        {1, 32, 23, 1, 19, 14, 31, 21, 7, 22 },
        {8, 21, 35, 25, 29, 3, 33, 25, 27, 21 },
    };
    public static final Map<LocalDate, int[]> test_list4_using_default_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_list4_using_default_seed.put(TEST_DATES[i], l4_default[i]);
        }
    }


    private static final int[][] l5_default = {
        {2, 11, 14, 18, 27, 32, 21, 35, 3, 5 },
        {24, 2, 10, 2, 22, 21, 21, 3, 2, 13 },
        {20, 7, 1, 6, 30, 28, 34, 20, 15, 27 },
        {23, 30, 13, 2, 1, 18, 23, 1, 14, 31 },
        {1, 15, 3, 18, 9, 4, 27, 4, 15, 24 },
        {6, 4, 34, 3, 22, 19, 27, 21, 0, 23 },
        {13, 29, 10, 27, 32, 8, 29, 25, 20, 22 },
    };
    public static final Map<LocalDate, int[]> test_list5_using_default_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_list5_using_default_seed.put(TEST_DATES[i], l5_default[i]);
        }
    }

    public static final int[] test_num8_using_default_seed = { 4, 0, 2, 0, 4, 1, 5 };

    private static final int[][] idx_default = {
        {2, 11, 14, 18, 27, 32, 21, 35, 3, 5 },
        {24, 2, 10, 2, 22, 21, 21, 3, 2, 13 },
        {20, 7, 1, 6, 30, 28, 34, 20, 15, 27 },
        {23, 30, 13, 2, 1, 18, 23, 1, 14, 31 },
        {1, 15, 3, 18, 9, 4, 27, 4, 15, 24 },
        {6, 4, 34, 3, 22, 19, 27, 21, 0, 23 },
        {13, 29, 10, 27, 32, 8, 29, 25, 20, 22 },
    };
    public static final Map<LocalDate, int[]> test_indexers_using_default_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_indexers_using_default_seed.put(TEST_DATES[i], idx_default[i]);
        }
    }



    /**
     * Data for custom seed lists.
     */

    public static final int[] test_list2_using_custom_seed = {29, 30, 31, 32, 33, 34, 35, 0};

    private static final int[][] l3_custom = {
        {27, 21, 11, 19, 27, 17, 7, 3, 24, 0 },
        {7, 16, 19, 13, 10, 18, 6, 9, 26, 4 },
        {8, 9, 19, 16, 21, 19, 5, 15, 4, 16 },
        {6, 8, 22, 28, 7, 20, 4, 21, 8, 4 },
        {16, 26, 19, 25, 26, 35, 26, 7, 0, 0 },
        {7, 23, 5, 17, 26, 0, 25, 14, 9, 9 },
        {27, 21, 11, 19, 27, 1, 24, 21, 7, 1 },
    };
    public static final Map<LocalDate, int[]> test_list3_using_custom_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_list3_using_custom_seed.put(TEST_DATES[i], l3_custom[i]);
        }
    }

    private static final int[][] l4_custom = {
        {27, 21, 11, 0, 19, 27, 17, 7, 3, 24 },
        {9, 19, 26, 4, 10, 16, 6, 7, 13, 18 },
        {21, 15, 8, 16, 19, 19, 16, 9, 4, 5 },
        {21, 22, 8, 4, 7, 8, 4, 6, 28, 20 },
        {16, 26, 19, 0, 25, 26, 35, 26, 7, 0 },
        {25, 17, 0, 9, 23, 9, 5, 14, 26, 7 },
        {21, 27, 19, 1, 27, 21, 7, 11, 1, 24 },
    };
    public static final Map<LocalDate, int[]> test_list4_using_custom_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_list4_using_custom_seed.put(TEST_DATES[i], l4_custom[i]);
        }
    }

    private static final int[][] l5_custom = {
        {20, 15, 6, 32, 16, 25, 16, 7, 4, 26 },
        {2, 13, 21, 0, 7, 14, 5, 7, 14, 20 },
        {14, 9, 3, 12, 16, 17, 15, 9, 5, 7 },
        {14, 16, 3, 0, 4, 6, 3, 6, 29, 22 },
        {9, 20, 14, 32, 22, 24, 34, 26, 8, 2 },
        {18, 11, 31, 5, 20, 7, 4, 14, 27, 9 },
        {14, 21, 14, 33, 24, 19, 6, 11, 2, 26 },
    };
    public static final Map<LocalDate, int[]> test_list5_using_custom_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_list5_using_custom_seed.put(TEST_DATES[i], l5_custom[i]);
        }
    }

    private static final int[] test_num8_using_custom_seed = {0, 2, 4, 2, 0, 3, 1};

    private static final int[][] indexers_custom = {
        {20, 15, 6, 32, 16, 25, 16, 7, 4, 26 },
        {2, 13, 21, 0, 7, 14, 5, 7, 14, 20 },
        {14, 9, 3, 12, 16, 17, 15, 9, 5, 7 },
        {14, 16, 3, 0, 4, 6, 3, 6, 29, 22 },
        {9, 20, 14, 32, 22, 24, 34, 26, 8, 2 },
        {18, 11, 31, 5, 20, 7, 4, 14, 27, 9 },
        {14, 21, 14, 33, 24, 19, 6, 11, 2, 26 },
    };
    public static final Map<LocalDate, int[]> test_indexers_using_custom_seed = new HashMap<>();
    static {
        for (int i = 0; i < TEST_DATES.length; i++) {
            test_indexers_using_custom_seed.put(TEST_DATES[i], indexers_custom[i]);
        }
    }

}
