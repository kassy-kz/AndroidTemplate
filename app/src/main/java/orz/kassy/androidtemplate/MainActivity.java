package orz.kassy.androidtemplate;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private Object[] activities = {
            "RailsAccess", RailsAccessActivity.class,
            "CardView", CardViewActivity.class,
            "DesignButton", DesignButtonActivity.class,
            "PercentRelative", PercentRelativeActivity.class,
            "Contact Provider2", ContactProvider2Activity.class,
            "Contact Provider", ContactProviderActivity.class,
            "Face Tracker", FaceTrackerActivity.class,
            "Activity Transition", TransitionActivity.class,
            "Picker", PickerActivity.class,
            "WeatherForecast", WeatherForecastActivity.class,
            "MaterialAnimation1", MaterialAnimation1Activity.class,
            "NavigationDrawer(Default)", NavigationDrawerDefaultActivity.class,
            "QR Code", QrCaptureActivity.class,
            "BackActionBar", BackActionBarActivity.class,
            "ButterKnife", ButterKnifeActivity.class,
            "BooksDB", BooksDBActivity.class,
            "SwipeRefreshLayout", SwipeRefreshLayoutActivity.class,
            "Snackbar", SnackbarActivity.class,
            "MainServiceControl", MainServiceControlActivity.class,
            "NotificationCompat", NotificationCompatActivity.class,
            "NavigationView", NavigationViewActivity.class,
            "TabLayout", TabLayoutActivity.class,
            "TabLayout2", TabLayout2Activity.class,
            "Design Support Library", DesignLibraryActivity.class,
            "Navigation Drawer ", NavigationDrawerActivity.class,
            "Navigation Drawer2 ", NavigationDrawer2Activity.class,
            "Navigation Drawer3(Deprecated)", NavigationDrawer3Activity.class,
    };

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] list = new String[activities.length/2];
        for (int i = 0; i < list.length; i++) {
            list[i] = (String)activities[i * 2];
        }
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_row, list);
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(MainActivity.this, (Class<?>)activities[position * 2 + 1]);
        startActivity(intent);
    }

}
