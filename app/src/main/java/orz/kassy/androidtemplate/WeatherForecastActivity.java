package orz.kassy.androidtemplate;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import orz.kassy.androidtemplate.weather.ImageLoaderTask;
import orz.kassy.androidtemplate.weather.WeatherApi;
import orz.kassy.androidtemplate.weather.WeatherForecast;

public class WeatherForecastActivity extends AppCompatActivity {

    private static final String[] POINT_LIST = {
            "270000",
            "130010",
            "040010"};

    private List<String> pointList;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (pointList == null) {
            pointList = Arrays.asList(POINT_LIST);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // return PlaceholderFragment.newInstance(position + 1);
            return WeatherFragment.newInstance(pointList.get(position));

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }


    public static class WeatherFragment extends Fragment {

        private static final String KEY_CITY_CODE = "key_city_code";

        public static WeatherFragment newInstance(String cityCode) {
            WeatherFragment fragment = new WeatherFragment();
            Bundle args = new Bundle();
            args.putString(KEY_CITY_CODE, cityCode);
            fragment.setArguments(args);

            return fragment;
        }

        private TextView location;
        private LinearLayout forecastLayout;
        private ProgressBar progress;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_weather_forecast, null);

            location = (TextView) view.findViewById(R.id.tv_location);
            forecastLayout = (LinearLayout) view.findViewById(R.id.ll_forecasts);
            progress = (ProgressBar) view.findViewById(R.id.progress);

            new GetWeatherForecastTask(getActivity()).execute(getArguments().getString(KEY_CITY_CODE));

            return view;
        }

        private class GetWeatherForecastTask extends AsyncTask<String, Void, WeatherForecast> {
            private Context mContext;
            Exception mException;

            public GetWeatherForecastTask(Context context) {
                mContext = context;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(WeatherForecast data) {
                super.onPostExecute(data);

                if (data != null) {
                    progress.setVisibility(View.GONE);

                    location.setText(data.location.area + " " + data.location.prefecture + " " + data.location.city);

                    // 予報を一覧表示
                    for (WeatherForecast.Forecast forecast : data.forecastList) {
                        View row = View.inflate(getActivity(), R.layout.view_forecasts_row, null);

                        TextView date = (TextView) row.findViewById(R.id.tv_date);
                        date.setText(forecast.dateLabel);

                        TextView telop = (TextView) row.findViewById(R.id.tv_telop);
                        telop.setText(forecast.telop);

                        TextView temp = (TextView) row.findViewById(R.id.tv_tempreture);
                        temp.setText(forecast.temperature.toString());

                        ImageView imageView = (ImageView) row.findViewById(R.id.iv_weather);

                        // 読み込み処理の実行
                        ImageLoaderTask task = new ImageLoaderTask(getActivity(), forecast.image.url);
                        task.execute(imageView);

                        forecastLayout.addView(row);
                    }

                } else if (mException != null) {
                    Toast.makeText(getActivity(), mException.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected WeatherForecast doInBackground(String... params) {
                try {
                    return WeatherApi.getWeather(mContext, params[0]);
                } catch (IOException e) {
                    mException = e;
                } catch (JSONException e) {
                    mException = e;
                }
                return null;
            }
        }
    }
}
