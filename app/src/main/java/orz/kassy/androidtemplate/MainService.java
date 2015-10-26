package orz.kassy.androidtemplate;

import orz.kassy.androidtemplate.IMainService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class MainService extends Service{
    
    private static final String TAG = "MainService";
    private Handler handler;
    private NotificationManager mNM;
    private static Context sContext;
    private static final int NOTIFICATION = 8080;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        Toast.makeText(this, "MainService onCreate", Toast.LENGTH_LONG).show();
        handler = new Handler();
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        showNotification();
        sContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i(TAG, "onStart");
        Toast.makeText(this, "MainService onStart", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        Toast.makeText(this, "MainService onBind", Toast.LENGTH_LONG).show();
        return ImainServiceBinder;
    }
    
    private final IMainService.Stub ImainServiceBinder = new IMainService.Stub(){
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            // do nothing
        }

        public void callFunction(){
            Log.i(TAG,"called callFunction");
            Toast.makeText(sContext, "MainService callFunction", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(sContext, "onUnbind", Toast.LENGTH_LONG).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(sContext, "onDestroy", Toast.LENGTH_LONG).show();
        mNM.cancel(NOTIFICATION);
    }

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = "local service start";

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainServiceControlActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("local_service")  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();

        // Send the notification.
        mNM.notify(NOTIFICATION, notification);
    }
}