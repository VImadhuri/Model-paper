package com.example.test.DataBase;

import android.provider.BaseColumns;

public final class UserProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_NAME_1 = "userName";
        public static final String COLUMN_NAME_2 = "dateOfBirth";
        public static final String COLUMN_NAME_4= "Password";
        public static final String COLUMN_NAME_3= "Gender ";
    }
}
