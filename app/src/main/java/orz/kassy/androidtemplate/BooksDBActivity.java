package orz.kassy.androidtemplate;

import java.util.UUID;

import orz.kassy.androidtemplate.database.BooksDatabase;
import orz.kassy.androidtemplate.database.BooksDatabase.DataColumns;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class BooksDBActivity extends Activity implements OnClickListener{
	
	private static final String TAG="tmplMain";
	private TextView  headText;

	private BooksDatabase mBooksDB;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_db);

        Button btn1=(Button)findViewById(R.id.books_db_insert);
    	btn1.setOnClickListener(this);
        Button btn2=(Button)findViewById(R.id.books_db_query);
        btn2.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
	    switch (v.getId()) {
            case R.id.books_db_insert:
                Log.i(TAG,"btn1 pressed");
                insertDatabase();
                break;
            case R.id.books_db_query:
                queryDatabase();
                break;

            default:
                break;
        }
	}
	
	private void queryDatabase() {
        Cursor cursor;
        cursor = mBooksDB.query();

        // カーソルのライフサイクル管理を
        // アクティビティに任せます。
        startManagingCursor(cursor);

        // from: カラム名の配列
        String[] from = {DataColumns.TITLE, DataColumns.ISBN};

        // to: fromに対応するビューのIDの配列
        int[] to = { R.id.name_in_list, R.id.age_in_list };

        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(getApplicationContext(),
                                          R.layout.view_db_list_row,
                                          cursor,
                                          from,
                                          to,
                                          CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        ListView listView = (ListView) findViewById(R.id.bookd_db_list_view);
        listView.setAdapter(adapter);
   
    }

    @Override
	public void onResume() {
	    super.onResume();
        mBooksDB = new BooksDatabase(this);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    mBooksDB.close();
	}
	
	private void insertDatabase(){
	    ContentValues values = new ContentValues();
	    String randStr = UUID.randomUUID().toString();
        double randNum = Math.random();

        // ランダムな値を入れる
	    values.put(DataColumns.TITLE, randStr);
        values.put(DataColumns.ISBN, String.valueOf(randNum));
        long id = mBooksDB.insert(values);
 	}
	
}