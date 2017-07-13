package com.pyf.latte.ec.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库管理器
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/12
 */
public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mUserProfileDao;

    private DatabaseManager() {

    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fastEc.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public UserProfileDao getUserProfileDao() {
        return mUserProfileDao;
    }
}
