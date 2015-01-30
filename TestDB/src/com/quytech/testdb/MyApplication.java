package com.quytech.testdb;



import com.quytech.db.JDBcH2Connection;

import android.app.Application;

public class MyApplication extends Application
{
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		JDBcH2Connection.getInstance();
	}
}
