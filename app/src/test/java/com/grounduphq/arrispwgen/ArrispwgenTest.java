package com.grounduphq.arrispwgen;

import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDate;

import java.util.Map;

import static com.grounduphq.arrispwgen.Constants.DEFAULT_SEED;
import static com.grounduphq.arrispwgen.DataHelper.CUSTOM_SEED;
import static com.grounduphq.arrispwgen.DataHelper.using_default_seed;
import static com.grounduphq.arrispwgen.DataHelper.using_custom_seed;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ArrispwgenTest {
    private Arrispwgen a;

    @Before
    public void setUp() throws Exception {
        this.a = new Arrispwgen();
    }

    // DRY function for testing single password
    private void test_single_passwords(Map<LocalDate, String> data, String seed) {
        for (Map.Entry<LocalDate, String> p: data.entrySet()) {
            LocalDate date = p.getKey();
            String potd = p.getValue();

            String test_potd = this.a.generate(date, seed);

            assertEquals(potd, test_potd);
        }
    }

    @Test
    public void passwords_with_default_seed_are_correct() {
        test_single_passwords(using_default_seed, DEFAULT_SEED);
    }

    @Test
    public void passwords_with_custom_seed_are_correct() {
        test_single_passwords(using_custom_seed, CUSTOM_SEED);
    }
}
