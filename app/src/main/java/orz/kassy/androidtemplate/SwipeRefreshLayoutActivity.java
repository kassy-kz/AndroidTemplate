package orz.kassy.androidtemplate;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.os.Handler;

import java.util.Arrays;
import java.util.LinkedList;

public class SwipeRefreshLayoutActivity extends ActionBarActivity {

    private final SwipeRefreshLayoutActivity self = this;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    // 最初のリスト
    private static final String[] INITIAL_LIST = {
            "最初に", "表示される", "リストの", "項目で", "あります",
    };
    private LinkedList<String> mItemList;
    private ArrayAdapter<String> mAdapter;

    // リスト項目追加の目印
    private static int sAddCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        // SwipeRefreshLayoutの設定
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.view_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

        // ListViewにデータをセットする
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(self, R.array.entries_list_preference, android.R.layout.simple_list_item_1);
        mItemList = new LinkedList<String>();
        mItemList.addAll(Arrays.asList(INITIAL_LIST));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItemList);

        ListView listView = (ListView) findViewById(R.id.view_refresh_list);
        listView.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new GetDataTask().execute();
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

    /**
     * リストを引っ張った時にデータを取得しにいくワーカータスク
     */
    private class GetDataTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // ここでデータ取得処理（ここではスタブ的に一定時間待機）
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // リストにデータ追加
            mItemList.addFirst(sAddCount++ + "番目に追加された項目");
            mAdapter.notifyDataSetChanged();
            // カスタムリストビューに完了を伝える
            mSwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(result);
        }
    }
}
