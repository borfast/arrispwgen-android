package com.grounduphq.arrispwgen;

import org.junit.Test;
import org.threeten.bp.LocalDate;

import static com.grounduphq.arrispwgen.Data.list1;
import static com.grounduphq.arrispwgen.DataHelper.TEST_DATES;
import static com.grounduphq.arrispwgen.DataHelper.test_list1;
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
}
