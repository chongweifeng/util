package com.chong.util;

public class TextProcessUtil {

    public static String segmentWords2TagWords(String line) {
        StringBuilder builder = new StringBuilder();
        boolean start = true;
        for (int i = 0; i < line.length(); i++) {
            String oneCharString = line.substring(i, i + 1);
            if (oneCharString.matches("\\s")) {
                start = true;
                continue;
            }
            if (start) {
                builder.append(oneCharString);
                builder.append("/1");
                start = false;
            } else {
                builder.append(oneCharString);
                builder.append("/0");
            }
            builder.append(" ");
        }
        return builder.toString().trim();
    }

}
