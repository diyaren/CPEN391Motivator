package com.firebase.uidemo.auth;

import android.provider.BaseColumns;

/**
 * Created by Navjashan on 14/03/2017.
 */

public class TaskContract {

    public static final String DB_NAME = "com.firebase.uidemo.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns{
        public static final String TABLE = "tasks";
        public static final String COL_TASK_TITLE = "title";
    }

}
