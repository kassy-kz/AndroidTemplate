package orz.kassy.androidtemplate.weather;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApi {

    private static final String USER_AGENT = "WeatherForecasts Sample";

    private static final String URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";

    public static WeatherForecast getWeather(Context context, String pointId) throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        java.net.URL u = new URL(URL + pointId);
        InputStream is;
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        is = new BufferedInputStream(con.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return new WeatherForecast(new JSONObject(sb.toString()));
    }

}
