package com.grounduphq.arrispwgen;

import org.junit.Test;
import org.threeten.bp.LocalDate;

import java.util.Map;

import static com.grounduphq.arrispwgen.Constants.DEFAULT_SEED;
import static com.grounduphq.arrispwgen.DataHelper.CUSTOM_SEED;
import static com.grounduphq.arrispwgen.DataHelper.TEST_DATES;
import static com.grounduphq.arrispwgen.DataHelper.potd_using_custom_seed;
import static com.grounduphq.arrispwgen.DataHelper.potd_using_default_seed;
import static org.junit.Assert.assertEquals;


public class ArrispwgenTest {

    // DRY function for testing single password
    private void test_single_passwords(Map<LocalDate, String> data, String seed) {
        for (Map.Entry<LocalDate, String> p: data.entrySet()) {
            LocalDate date = p.getKey();
            String potd = p.getValue();

            String test_potd = Arrispwgen.generate(date, seed);

            assertEquals(test_potd, potd);
        }
    }

    @Test
    public void passwords_with_default_seed_are_correct() {
        test_single_passwords(potd_using_default_seed, DEFAULT_SEED);
    }

    @Test
    public void passwords_with_custom_seed_are_correct() {
        test_single_passwords(potd_using_custom_seed, CUSTOM_SEED);
    }


    @Test(expected=IllegalArgumentException.class)
    public void throws_exception_if_start_date_after_end_date() {
        LocalDate d1 = LocalDate.of(2016, 1, 10);
        LocalDate d2 = LocalDate.of(2016, 1, 5);
        Arrispwgen.generate_multi(d1, d2, DEFAULT_SEED);
    }

    @Test
    public void generates_single_password_if_date_interval_just_one_date() {
        LocalDate d1 = LocalDate.of(2016, 1, 5);
        LocalDate d2 = LocalDate.of(2016, 1, 5);
        Map<LocalDate, String> potd_list = Arrispwgen.generate_multi(d1, d2, DEFAULT_SEED);

        assertEquals(1, potd_list.size());
    }


    private void test_multiple_passwords(Map<LocalDate, String> potd_test_table, int start_index, int end_index, String seed) {
        LocalDate start_date = TEST_DATES[start_index];
        LocalDate end_date = TEST_DATES[end_index];
        Map<LocalDate, String> potd_list = Arrispwgen.generate_multi(start_date, end_date, seed);

        int count = end_index - start_index + 1;
        assertEquals(count, potd_list.size());

        for (Map.Entry<LocalDate, String> entry : potd_list.entrySet()) {
            LocalDate date = entry.getKey();
            assertEquals(potd_test_table.get(date), entry.getValue());
        }
    }


    @Test
    public void generates_correct_passwords_with_default_seed() {
        test_multiple_passwords(potd_using_default_seed, 0, 3, DEFAULT_SEED);
        test_multiple_passwords(potd_using_default_seed, 4, 6, DEFAULT_SEED);
    }

    @Test
    public void generates_correct_passwords_with_custom_seed() {
        test_multiple_passwords(potd_using_custom_seed, 0, 3, CUSTOM_SEED);
        test_multiple_passwords(potd_using_custom_seed, 4, 6, CUSTOM_SEED);
    }
}
