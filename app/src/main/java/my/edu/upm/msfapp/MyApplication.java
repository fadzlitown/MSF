package my.edu.upm.msfapp;

import android.app.Application;
import android.support.v4.util.LruCache;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.jakewharton.threetenabp.AndroidThreeTen;


public class MyApplication extends Application {

    private static MyApplication instance;
    private JobManager jobManager;
    private static LruCache<String, String> lruCache;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        configureJobManager();

//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();


//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath(getString(R.string.font_regular))
//                .setFontAttrId(R.attr.fontPath)
//                .build());

        AndroidThreeTen.init(this);
    }


    private void configureJobManager() {
        Configuration.Builder builder = new Configuration.Builder(this)
                .minConsumerCount(1)// always keep at least one consumer alive
                .maxConsumerCount(3)// up to 3 consumers at a time
                .loadFactor(3)// 3 jobs per consumer
                .consumerKeepAlive(120);// wait 2 minute

        jobManager = new JobManager(builder.build());
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    public static void addJobInBackground(Job job) {
        MyApplication.getInstance().getJobManager().addJobInBackground(job);
    }

    public static LruCache<String, String> getLruCache() {
        if (lruCache == null) {
            final int maxMemory = ((int) (Runtime.getRuntime().maxMemory() / 1024));
            final int cacheSize = maxMemory / 8;

            lruCache = new LruCache<String, String>(cacheSize);
        }
        return lruCache;
    }


}