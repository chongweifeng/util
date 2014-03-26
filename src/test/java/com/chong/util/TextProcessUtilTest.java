package com.chong.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.chong.util.TextProcessUtil;

public class TextProcessUtilTest {
    
    
    @Test
    public void testSegmentWords2TagWords(){
        String taggedString=TextProcessUtil.segmentWords2TagWords(" aa  bb d");
        assertEquals("a/1 a/0 b/1 b/0 d/1",taggedString);
    }
    
}
