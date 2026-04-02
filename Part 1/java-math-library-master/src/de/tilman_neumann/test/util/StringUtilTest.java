package de.tilman_neumann.test.util;

import de.tilman_neumann.util.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class StringUtilTest {
    // 1.Repeat Method [repeat(String s, int n)]

    // Happy Scenarios
    // For Parameter s --> Normal
    @Test
    public void testRepeatNormal() {
        assertEquals("ssss", StringUtil.repeat("s", 4));
    }

    // For Parameter n --> Positive + valid BVA
    @Test
    public void testRepeatPositive() {
        assertEquals("a", StringUtil.repeat("a", 1));
    }
    // ---------------------------------------------------------------------
    // Bad Scenarios

    // For Parameter n --> Zero (ISP) + boundary (BVA)
    @Test
    public void testRepeatZero() {
        assertNull(StringUtil.repeat("a", 0));
    }
    // For Parameter n --> Negative (ISP) + invalid (BVA)
    @Test
    public void testRepeatNegative() {
        assertNull(StringUtil.repeat("a", -1));
    }
    // For Parameter n --> Large number
    @Test
    public void repeat_largeN() {
        String result = StringUtil.repeat("a", 1000);
        assertEquals(1000, result.length());
    }
    // For Parameter s --> Null
    @Test
    public void testRepeatNullString() {
        assertNull(StringUtil.repeat(null, 4));
    }
    // For Parameter s --> Empty edge
    @Test
    public void testRepeatEmptyString() {
        assertEquals("", StringUtil.repeat("", 6));
    }
    // For Parameter s --> unicode edge
    @Test
    public void repeat_unicode() {
        assertEquals("🔥🔥", StringUtil.repeat("🔥", 2));
    }
// --------------------------------------------------------------------------------------
    // 2.FormatLeft Method [formatLeft(String s, String mask)]
    // Happy Scenarios
    // // For Parameter s --> Normal
    @Test
    public void formatLeft_normal() {
        assertEquals("abc456", StringUtil.formatLeft("abc", "123456"));
    }

    // BVA
    @Test
    public void formatLeft_equalLength() {
        assertEquals("abc", StringUtil.formatLeft("abc", "123"));
    }

    @Test
    public void formatLeft_stringLonger() {
        assertEquals("abcdef", StringUtil.formatLeft("abcdef", "123"));
    }

    // ---------------------------------------------------------------------
    // Bad Scenarios
    // // For Parameter s --> Empty edge
    @Test
    public void formatLeft_nullString() {
        assertEquals("123", StringUtil.formatLeft(null, "123"));
    }

    @Test
    public void formatLeft_nullMask() {
        assertEquals("abc", StringUtil.formatLeft("abc", null));
    }

    @Test
    public void formatLeft_emptyString() {
        assertEquals("123", StringUtil.formatLeft("", "123"));
    }
    @Test
    public void formatLeft_emptyMask() {
        assertEquals("abc", StringUtil.formatLeft("abc", ""));
    }
//--------------------------------------------------------------------------------------

    // 3.FormatRight Method [formatRight(String s, String mask)]
    // Happy Scenarios
    void formatRight_normal() {
        assertEquals("123abc", StringUtil.formatRight("abc", "123456"));
    }

    @Test
    void formatRight_boundary_equalLength() {
        assertEquals("abc", StringUtil.formatRight("abc", "123"));
    }

    @Test
    void formatRight_stringLonger() {
        assertEquals("abcdef", StringUtil.formatRight("abcdef", "123"));
    }
    // ---------------------------------------------------------------------
    // Bad Scenarios
    // // For Parameter s --> Empty edge
    @Test
    public void formatRight_nullString() {
        assertEquals("123", StringUtil.formatRight(null, "123"));
    }

    @Test
    public void formatRight_nullMask() {
        assertEquals("abc", StringUtil.formatRight("abc", null));
    }

    @Test
    public void formatRight_emptyString() {
        assertEquals("123", StringUtil.formatRight("", "123"));
    }
    @Test
    public void formatRight_emptyMask() {
        assertEquals("abc", StringUtil.formatRight("abc", ""));
    }


}
