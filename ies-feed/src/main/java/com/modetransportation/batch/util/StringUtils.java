package com.modetransportation.batch.util;

public class StringUtils {

    public static boolean isStringEmpty(String val)
    {
        if ( val == null || val.trim().length() == 0 )
            return true;
        return false;
    }

}
