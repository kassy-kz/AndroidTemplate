package orz.kassy.androidtemplate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class BooksDatabase {

    public interface DataColumns extends BaseColumns {
        public static final String DISPLAY_NAME = "_display_name";
        public static final String TITLE = "title";
        public static final String _ID = "_id";
        public static final String ISBN = "isbn";        
    }
    
    private SQLiteDatabase mBooksDB;
    private static final String DATABASE_TABLE = "titles";
    
    /** 
     * Constructor
     */
    public BooksDatabase(Context context) {
        BooksDBHelper dbHelper = new BooksDBHelper(context);
        mBooksDB = dbHelper.getWritableDatabase();
    }
        
    public long insert(ContentValues values) {
        long rowID = mBooksDB.insert(DATABASE_TABLE, "", values);
        if(rowID > 0) {
            return rowID;
        }
        throw new SQLException("Failed to insert row");
    }

    public Cursor query() {
        Cursor cursor = mBooksDB.query(DATABASE_TABLE,  null, null, null, null, null, null);
        return cursor;
    }

    public void close(){
        mBooksDB.close();
    }
    
    private static class BooksDBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "books.db";

        private static final int DATABASE_VERSION = 2;

        private static final String DATABASE_CREATE = "create table " 
                 + DATABASE_TABLE + " (" 
                 + DataColumns._ID + " integer primary key autoincrement, "
                 + DataColumns._COUNT + " integer,"
                 + DataColumns.DISPLAY_NAME + " text,"
                 + DataColumns.ISBN + " text not null,"
                 + DataColumns.TITLE + " text not null"
                 + ");";
        
        public BooksDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP ABLE IF EXISTS titles");
            onCreate(db);
        }
    }
}
