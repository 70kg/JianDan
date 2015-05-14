package com.socks.jiandan.cache;


import com.greendao.DaoSession;

import java.util.ArrayList;

/**
 * Created by zhaokaiqiang on 15/5/12.
 */
public abstract class BaseCacheUtil<T> {

	protected static DaoSession mDaoSession;

	public abstract void clearAllCache();

	public abstract ArrayList<T> getCacheByPage(int page);

	public abstract void addResultCache(String result, int page);

}
