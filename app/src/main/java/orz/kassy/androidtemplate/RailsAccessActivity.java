package orz.kassy.androidtemplate;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RailsAccessActivity extends AppCompatActivity {

    private static final String RAILS_URL = "https://shielded-atoll-7166.herokuapp.com/members";
    private static final String RAILS_POST = "{'name':'aaaaaaa','comment':'ace aaaaa'}";
    private static final String TAG = "Rails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rails_access);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btnRailsPost)
    void onClickRaisPost() {
        new PostTask(this).execute();
    }


    private class PostTask extends AsyncTask<Void, Void, Void> {
        private Context mContext;
        Exception mException;

        public PostTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                railsPost2();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void railsPost2() throws IOException {
        URL url;
        HttpURLConnection urlConn;
        DataOutputStream printout;
        DataInputStream input;
        url = new URL (RAILS_URL);
        urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setDoInput (true);
        urlConn.setDoOutput (true);
        urlConn.setUseCaches (false);
        urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//        urlConn.setRequestProperty("Host", "android.schoolportal.gr");
        urlConn.connect();
        //Create JSONObject here
        JSONObject jsonParam = new JSONObject();
        try {
            JSONObject parent=new JSONObject();
            parent.put("name", "name");
            parent.put("comment", "comment");

            OutputStream os = urlConn.getOutputStream();
            os.write(parent.toString().getBytes("UTF-8"));
            os.close();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"res="+urlConn.getResponseCode());

    }

    public void railsPost() throws IOException, JSONException {
        Log.i(TAG,"railspost");
        URL url = new URL(RAILS_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("charset", "utf-8");

        urlConnection.connect();

//        DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
//        os.write(RAILS_POST.getBytes("UTF-8"));
//        os.flush();
//        os.close();

//        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//        out.write(RAILS_POST.getBytes("UTF-8"));
//        out.flush();
//        out.close();
//        urlConnection.disconnect();


        Log.i(TAG,"res="+urlConnection.getResponseCode());
    }

}
