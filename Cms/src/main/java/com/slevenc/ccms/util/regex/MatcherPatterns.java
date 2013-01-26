package com.slevenc.ccms.util.regex;

import org.springframework.context.annotation.Bean;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 下午7:12
 * To change this template use File | Settings | File Templates.
 */
public class MatcherPatterns {

    private Pattern[] onPatterns = null;
    private Pattern[] offPatterns = null;

    public void setOnPatterns(String[] onPatterns) {
        if (onPatterns != null) {
            Pattern[] data = new Pattern[onPatterns.length];
            for (int i = 0; i < onPatterns.length; i++) {
                data[i] = Pattern.compile(onPatterns[i]);
            }
            this.onPatterns = data;
        }
    }

    public void setOffPatterns(String[] offPatterns) {
        if (offPatterns != null) {
            Pattern[] data = new Pattern[offPatterns.length];
            for (int i = 0; i < offPatterns.length; i++) {
                data[i] = Pattern.compile(offPatterns[i]);
            }
            this.offPatterns = data;
        }
    }

    public boolean isMatch(String text) {
        boolean result = false;
        for (Pattern on : onPatterns) {
            if (on.matcher(text).matches()) {
                result = true;
                for (Pattern off : offPatterns) {
                    if (off.matcher(text).matches()) {
                        result = false;
                        break;
                    }
                }
                break;
            }
        }
        return result;
    }


}
