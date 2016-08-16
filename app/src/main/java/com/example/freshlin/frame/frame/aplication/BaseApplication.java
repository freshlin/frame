package com.example.freshlin.frame.frame.aplication;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.util.Log;

import com.example.freshlin.frame.frame.utils.ShareUtils;
import com.example.freshlin.frame.frame.utils.CommonUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by freshlin on 2016/8/6.
 */
public class BaseApplication extends Application{

    private List<Activity> acts;
    private HashMap<String, Object> hashMap;

    private BroadcastReceiver receiver;


    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String processName  = CommonUtils.getProccesName(this, Process.myPid());

        if(processName != null) {
            Log.e("xxxx", "Application start! process:" + processName);
            boolean defaultProcess = TextUtils.equals(processName, this.getPackageName());
            if(defaultProcess) {
                this.initAppForMainProcess();
            } else {
                this.initAppForOtherProcess(processName);
            }
        }

    }


    public void onTerminate() {
        if(this.receiver != null) {
            try {
                this.unregisterReceiver(this.receiver);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        super.onTerminate();
    }

    public void onLowMemory() {
        super.onLowMemory();
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void addActivity(Activity activity) {
        if(!acts.contains(activity))
            this.acts.add(activity);
    }

    public void exit() {
        this.clear();
        System.exit(0);
    }

    public void remove(Activity activity) {
        if(this.acts != null && !this.acts.isEmpty()) {
            this.acts.remove(activity);
        }

    }

    public void removeFinish(Class cls) {
        if(this.acts != null && !this.acts.isEmpty()) {
            Activity act = null;
            Iterator var3 = this.acts.iterator();

            while(var3.hasNext()) {
                Activity item = (Activity)var3.next();
                if(TextUtils.equals(item.getClass().getName(), cls.getName())) {
                    act = item;
                    break;
                }
            }

            if(act != null) {
                act.finish();
                this.acts.remove(act);
            }
        }

    }

    public void clear() {
        if(this.acts != null && !this.acts.isEmpty()) {
            Iterator var1 = this.acts.iterator();

            while(var1.hasNext()) {
                Activity activity = (Activity)var1.next();
                activity.finish();
            }

            this.acts.clear();
        }

    }

    public void putValue(String key, Object value) {
        if(this.hashMap == null) {
            this.hashMap = new HashMap();
        }

        this.hashMap.put(key, value);
    }

    public Object getValue(String key) {
        return this.hashMap != null?this.hashMap.get(key):null;
    }

    @CallSuper
    protected void initAppForMainProcess() {
        this.initAppForMainProcess((IntentFilter)null);

    }

    @CallSuper
    protected void initAppForMainProcess(IntentFilter filter) {
        if(filter == null) {
            filter = new IntentFilter();
        }

        if(!filter.hasAction("android.net.conn.CONNECTIVITY_CHANGE")) {
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }

        filter.setPriority(-1000);
        if(this.receiver == null) {
            this.receiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();

                    if(TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE")) {
                        ConnectivityManager manager = (ConnectivityManager)BaseApplication.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                        int result = -1;
                        NetworkInfo netInfo = manager.getActiveNetworkInfo();
                        if(netInfo != null && netInfo.isConnected()) {
                            result = netInfo.getType();
                        }

                        (ShareUtils.getInstace(getBaseContext())).putValue("NET_STATUS", result);
                        BaseApplication.this.sendBroadcast(new Intent("com.fl.frame.ACTION_NET_CHANGE"));
                    }

                    BaseApplication.this.onReceive(this, intent);
                }
            };
        }

        this.registerReceiver(this.receiver, filter);
        ShareUtils.getInstace(getApplicationContext()).putValue("NET_STATUS", 1);
        this.acts = new CopyOnWriteArrayList();
        this.hashMap = new HashMap();
    }


    protected void initAppForOtherProcess(String process) {
    }

    protected void onReceive(BroadcastReceiver receiver, Intent intent) {

    }




}
