package orz.kassy.androidtemplate;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainServiceControlActivity extends AppCompatActivity implements OnClickListener{
    
    private static final String TAG="MainActivity";
    private static TextView  headText;
    private IMainService mBinder;
    private View mViewRoot;
    private MainService mBoundService;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service_control);
        
        Button btnStart = (Button)findViewById(R.id.btnServiceStart);
        btnStart.setOnClickListener(this);

        Button btnFuncCall = (Button) findViewById(R.id.btnServiceFuncCall);
        btnFuncCall.setOnClickListener(this);

        Button btnStop = (Button)findViewById(R.id.btnServiceStop);
        btnStop.setOnClickListener(this);

        mViewRoot = findViewById(R.id.view_activity_root);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnServiceStart:
                Log.i(TAG,"btnStart pressed");
                Intent intent = new Intent(this, MainService.class);
//                startService(intent);
                // Attach Service
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;

            case R.id.btnServiceFuncCall:
                Log.i(TAG,"btnCallFunc pressed");
                try{
                    if(mBinder != null){
                        mBinder.callFunction();
                        Snackbar.make(mViewRoot, "callFunction", Snackbar.LENGTH_SHORT).show();

                    }
                }catch(RemoteException e){
                    Snackbar.make(mViewRoot, "error", Snackbar.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;

            case R.id.btnServiceStop:
                Log.i(TAG,"btnStop pressed");
                Intent intent2 = new Intent(this, MainService.class);
//                stopService(intent2);

                unbindService(connection);
                break;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            Snackbar.make(mViewRoot, "onServiceConnected", Snackbar.LENGTH_SHORT).show();
            mBinder = IMainService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 意図せず死んだときだけ呼ばれるらしい
            Log.i(TAG,"onServiceDisconnected");
            Snackbar.make(mViewRoot, "onServiceDisconnected", Snackbar.LENGTH_SHORT).show();
            mBinder = null;
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}