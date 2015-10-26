package orz.kassy.androidtemplate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class BackActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_action_bar);

//        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
//
//        TypedValue outValue = new TypedValue();
//        getTheme().resolveAttribute(R.attr.colorPrimary, outValue, true);
//        TypedValue outValue2 = new TypedValue();
//        getTheme().resolveAttribute(R.attr.actionBarSize, outValue2, true);
//
//        Toolbar toolbar = new Toolbar(this);
//        toolbar.setBackgroundResource(outValue.resourceId);
//        toolbar.setBackgroundColor(Color.BLUE);
//        int actionBarHeight = TypedValue.complexToDimensionPixelSize(outValue2.data,getResources().getDisplayMetrics());
//        toolbar.setMinimumHeight(actionBarHeight);
//        toolbar.setPopupTheme(R.style.ToolbarTheme);
//
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        rootView.addView(toolbar, 0, params);
//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
