package com.septem.contact;

/**
 * Created by Septem on 2016/3/24.
 */
public class CheckDate {

    public static boolean isValid(String date) {
        if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            return true;
        } else {
            return false;
        }
    }
}
