package com.septem.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Septem on 2016/3/24.
 */
public class UserList {

    private static List<User> userList = new ArrayList<>();

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUserList(List<User> userList) {
        UserList.userList = userList;
    }
}
