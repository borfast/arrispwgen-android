package com.grounduphq.arrispwgen;

import org.junit.Test;
import org.threeten.bp.LocalDate;

import static com.grounduphq.arrispwgen.Constants.DEFAULT_SEED;
import static com.grounduphq.arrispwgen.Data.list1;
import static com.grounduphq.arrispwgen.Data.list2;
import static com.grounduphq.arrispwgen.Data.list3;
import static com.grounduphq.arrispwgen.Data.list4;
import static com.grounduphq.arrispwgen.Data.list5;
import static com.grounduphq.arrispwgen.Data.num8;
import static com.grounduphq.arrispwgen.DataHelper.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataTest {
    @Test
    public void list1_test() {
        for (LocalDate date: TEST_DATES) {
            int[] l1 = list1(date);
            assertArrayEquals(l1, test_list1.get(date));
        }
    }


    @Test
    public void list2_with_default_seed() {
        int[] l2 = list2(DEFAULT_SEED);
        assertArrayEquals(l2, test_list2_using_default_seed);
    }


    @Test
    public void list2_with_custom_seed() {
        int[] l2 = list2(CUSTOM_SEED);
        assertArrayEquals(l2, test_list2_using_custom_seed);
    }


    @Test
    public void list3_with_default_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(DEFAULT_SEED);
            int[] l3 = list3(l1, l2);

            assertArrayEquals(l3, test_list3_using_default_seed.get(date));
        }
    }


    @Test
    public void list3_with_custom_seed() {
        for (LocalDate date: TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(CUSTOM_SEED);
            int[] l3 = list3(l1, l2);

            assertArrayEquals(l3, test_list3_using_custom_seed.get(date));
        }
    }


    @Test
    public void list4_with_default_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(DEFAULT_SEED);
            int[] l3 = list3(l1, l2);

            int[] l4 = list4(l3);
            assertArrayEquals(l4, test_list4_using_default_seed.get(date));
        }
    }


    @Test
    public void list4_with_custom_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(CUSTOM_SEED);
            int[] l3 = list3(l1, l2);

            int[] l4 = list4(l3);
            assertArrayEquals(l4, test_list4_using_custom_seed.get(date));
        }
    }


    @Test
    public void list5_with_default_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(DEFAULT_SEED);
            int[] l3 = list3(l1, l2);
            int[] l4 = list4(l3);

            int[] l5 = list5(DEFAULT_SEED, l4);
            assertArrayEquals(l5, test_list5_using_default_seed.get(date));
        }
    }


    @Test
    public void list5_with_custom_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(CUSTOM_SEED);
            int[] l3 = list3(l1, l2);
            int[] l4 = list4(l3);

            int[] l5 = list5(CUSTOM_SEED, l4);
            assertArrayEquals(l5, test_list5_using_custom_seed.get(date));
        }
    }


    @Test
    public void num8_with_default_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(DEFAULT_SEED);
            int[] l3 = list3(l1, l2);

            int n8 = num8(l3);
            assertEquals(n8, test_num8_using_default_seed.get(date).intValue());
        }
    }


    @Test
    public void num8_with_custom_seed() {
        for (LocalDate date : TEST_DATES) {
            int[] l1 = list1(date);
            int[] l2 = list2(CUSTOM_SEED);
            int[] l3 = list3(l1, l2);

            int n8 = num8(l3);
            assertEquals(n8, test_num8_using_custom_seed.get(date).intValue());
        }
    }
}
