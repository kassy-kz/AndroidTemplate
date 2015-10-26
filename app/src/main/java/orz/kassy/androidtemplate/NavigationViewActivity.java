package orz.kassy.androidtemplate;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NavigationViewActivity extends AppCompatActivity {

    Activity sSelf;
    View viewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        viewMain = findViewById(R.id.view_navigation_view_main);

        sSelf = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView =
                (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.navigation_item_0:
                                menuItem.setChecked(true);
                                Snackbar.make(viewMain, "item 0", Snackbar.LENGTH_SHORT).show();                                return true;
                            case R.id.navigation_item_1:
                                menuItem.setChecked(true);
                                Snackbar.make(viewMain, "item 1", Snackbar.LENGTH_SHORT).show();                                return true;
                        }
                        return false;
                    }
                });
    }
}
