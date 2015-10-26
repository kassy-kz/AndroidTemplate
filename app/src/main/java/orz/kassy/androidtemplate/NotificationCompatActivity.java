package orz.kassy.androidtemplate;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class NotificationCompatActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_compat);

        Button btn1 = (Button) findViewById(R.id.btnNotification1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btnNotification2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btnNotification3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.btnNotification4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) findViewById(R.id.btnNotification5);
        btn5.setOnClickListener(this);

        Button btn6 = (Button) findViewById(R.id.btnNotification6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button) findViewById(R.id.btnNotification7);
        btn7.setOnClickListener(this);

        Button btn8 = (Button) findViewById(R.id.btnNotification8);
        btn8.setOnClickListener(this);
        Button btn9 = (Button) findViewById(R.id.btnNotification9);
        btn9.setOnClickListener(this);
        Button btn10 = (Button) findViewById(R.id.btnNotification10);
        btn10.setOnClickListener(this);
        Button btn11 = (Button) findViewById(R.id.btnNotification11);
        btn11.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent != null){
            String command = intent.getStringExtra("message");
            Log.v("intent", "Message: " + command);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNotification1:
                sendNotificationMinimum();
                break;
            case R.id.btnNotification2:
                sendNotificationBigIcon();
                break;
            case R.id.btnNotification3:
                sendNotificationVibration();
                break;
            case R.id.btnNotification4:
                sendNotificationApp();
                break;
            case R.id.btnNotification5:
                sendNotificationStyle();
                break;
            case R.id.btnNotification6:
                sendNotificationPictureStyle();
                break;
            case R.id.btnNotification7:
                sendNotificationInboxStyle();
                break;
            case R.id.btnNotification8:
                sendNotificationWithButton();
                break;
            case R.id.btnNotification9:
                break;
            case R.id.btnNotification10:
                break;
            case R.id.btnNotification11:
                break;
        }
    }

    // アイコンとタイトルをステータスバーに表示する
    private void sendNotificationMinimum() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // タイトルの設定
        builder.setContentTitle("ノーティフィケーションの最小表示");
        builder.setContentText("コンテンツの内容");
        builder.setContentInfo("情報欄");
        builder.setTicker("アプリからの通知概要");

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.colorAccent, outValue, true);

        builder.setColor(outValue.resourceId); // 0x0042A5F5, argb

        // マネージャをつかって通知する
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    // 大きなアイコンの通知を表示する
    private void sendNotificationBigIcon() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
            new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 大きなアイコンを設定
        builder.setContentTitle("大きなアイコンで表示");
        Bitmap largeIcon = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);
        // マネージャをつかって通知する
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    // バイブレーションをつかって通知する
    private void sendNotificationVibration() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
            new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 振動して通知
        builder.setContentTitle("バイブレーション付き");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS);
        builder.setTicker("アプリからの通知概要");

        // マネージャをつかって通知する
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    // 通知をクリックしたらアプリを起動する
    private void sendNotificationApp() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("通知からアプリを起動する");
        // アプリを起動するインテントを登録する
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(
                NotificationCompatActivity.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);
        // NotificationManagerを取得
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    // スタイルを適用する
    private void sendNotificationStyle() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
        new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // スタイルを適用する
        NotificationCompat.BigTextStyle bigTextStyle =
                new NotificationCompat.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle("BigTextStyle を利用する");
        bigTextStyle.bigText("コンテンツのテキスト");
        bigTextStyle.setSummaryText("通知内容のサマリ");
        // NotificationManagerを取得
        NotificationManager manager = (NotificationManager)
        getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    // スタイルを適用する
    private void sendNotificationPictureStyle() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
        new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // スタイルを適用する
        NotificationCompat.BigPictureStyle bigPictureStyle =
                new NotificationCompat.BigPictureStyle(builder);
        Bitmap largePicture = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        bigPictureStyle.bigPicture(largePicture);
        bigPictureStyle.setBigContentTitle("BigPictureStyle を利用する");
        bigPictureStyle.setSummaryText("通知内容のサマリ");
        // NotificationManagerを取得
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    // インボックススタイルを適用する
    private void sendNotificationInboxStyle() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder( getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // スタイルを適用する
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle(builder);
        inboxStyle.setBigContentTitle("InboxStyle を利用する");
        inboxStyle.setSummaryText("通知内容のサマリ");
        inboxStyle.addLine("複数行で表示する (1)");
        inboxStyle.addLine("複数行で表示する (2)");
        // NotificationManagerを取得
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    // ノーティフィケーションにボタンを追加する
    private void sendNotificationWithButton() {
        // ビルダーを経由してノーティフィケーションを作成
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("ボタンを追加する");
        // DELボタンを追加する
        Intent intent = new Intent(
                getApplicationContext(), MainActivity.class);
        intent.putExtra("message", "DEL");
        PendingIntent pendingIntent = PendingIntent.getActivity(
                NotificationCompatActivity.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.ic_input_delete,
                "DEL", pendingIntent);
        // ADDボタンを追加する
        Intent addIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        addIntent.putExtra("message", "ADD");
        PendingIntent addPendingIntent = PendingIntent.getActivity(
                NotificationCompatActivity.this, 0, addIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.ic_input_add,
                "Add", addPendingIntent);
        // マネージャをつかって通知する
        builder.setAutoCancel(true);
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


}
