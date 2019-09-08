package com.ssvtech.jalforce1.Utility;

import android.content.Context;
        import java.util.Timer;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import android.os.AsyncTask;
import android.app.ActivityManager;
import java.util.List;

public class LogOutTimerUtil {

    public interface LogOutListener {
        void doLogout();

    }

    static Timer longTimer;
    static final int LOGOUT_TIME = 1800000; // set to 30 mins

    public static synchronized void startLogoutTimer(final Context context, final LogOutListener logOutListener) {
        if (longTimer != null) {
            longTimer.cancel();
            longTimer = null;
        }
        if (longTimer == null) {

            longTimer = new Timer();

            longTimer.schedule(new TimerTask() {

                public void run() {

                    cancel();

                    longTimer = null;

                    try {
                        boolean foreGround = new ForegroundCheckTask().execute(context).get();

                        if (foreGround) {

                            System.out.println("LOGOUT HAS BEEN CALLED");
                            logOutListener.doLogout();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            }, LOGOUT_TIME);
        }
    }

    public static synchronized void stopLogoutTimer() {
        if (longTimer != null) {
            longTimer.cancel();
            longTimer = null;
        }
    }

    static class ForegroundCheckTask extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... params) {
            final Context context = params[0].getApplicationContext();
            return isAppOnForeground(context);
        }

        private boolean isAppOnForeground(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if (appProcesses == null) {
                return false;
            }
            final String packageName = context.getPackageName();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(packageName)) {
                    return true;
                }
            }
            return false;
        }
    }

}


