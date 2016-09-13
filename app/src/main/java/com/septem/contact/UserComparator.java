package com.septem.contact;

import java.util.Comparator;

/**
 * Created by Septem on 2016/3/25.
 */
public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {

        return user1.getFirstName().compareTo(user2.getFirstName());
    }
}
