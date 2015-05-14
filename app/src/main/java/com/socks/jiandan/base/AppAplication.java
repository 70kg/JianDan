package com.socks.jiandan.base;

import android.app.Application;
import android.content.Context;

import com.greendao.DaoMaster;
import com.greendao.DaoSession;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.socks.jiandan.cache.DateBaseInfo;
import com.socks.jiandan.utils.logger.Logger;

public class AppAplication extends Application {

	private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		initImageLoader();
		Logger.init().hideThreadInfo();
	}

	public static Context getContext() {
		return mContext;
	}

	// 初始化ImageLoader
	public static void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.writeDebugLogs()
				.build();
		ImageLoader.getInstance().init(config);
	}
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DateBaseInfo.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}