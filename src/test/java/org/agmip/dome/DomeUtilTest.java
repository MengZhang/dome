package org.agmip.dome;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DomeUtilTest {
    private HashMap<String, Object> dome = new HashMap<String, Object>();

    @Before
    public void setup() {
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("region", "SAMPLE");
        info.put("stratum", "99");
        info.put("man_id", "81");
        dome.put("info", info);

        ArrayList<HashMap<String, String>> rules = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> rule = new HashMap<String, String>();
        rule.put("cmd", "FILL");
        rule.put("variable", "exname");
        rule.put("args", "Sample");
        rules.add(rule);
        dome.put("rules", rules);
    }


    @Test
    public void testGetDomeInfo() {
        HashMap<String, String> test = new HashMap<String, String>();
        test.put("region", "SAMPLE");
        test.put("stratum", "99");
        test.put("man_id", "81");


        assertEquals("does not match test info", test, DomeUtil.getDomeInfo(dome));
    }

    @Test
    public void testGetDomeBlankInfo() {
        dome.put("info", null);
        assertEquals("doesn't return blank info", new HashMap<String, String>(), DomeUtil.getDomeInfo(dome));
    }

    @Test
    public void testGetDomeRules() {
        ArrayList<HashMap<String, String>> test = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> rule = new HashMap<String, String>();
        rule.put("cmd", "FILL");
        rule.put("variable", "exname");
        rule.put("args", "Sample");
        test.add(rule);

        assertEquals("does not match test rule", test, DomeUtil.getDomeRules(dome));
    }

    @Test
    public void testGetDomeBlankRules() {
        dome.put("rules", null);
        assertEquals("doesn't return blank rules", new ArrayList<HashMap<String, String>>(), DomeUtil.getDomeRules(dome));
    }

    @Test
    public void testGenerateDomeName() {
        String test = "SAMPLE-99--81-";
        assertEquals("incorrect name generated", test, DomeUtil.generateDomeName(dome));
    }

    @Test
    public void testUnpackDomeName() {
        String test = "SAMPLE-99--81-";
        assertEquals("incorrect unpacked values", DomeUtil.getDomeInfo(dome), DomeUtil.unpackDomeName(test));
    }

    @After
    public void cleanUp() {
        dome = null;
    }
}