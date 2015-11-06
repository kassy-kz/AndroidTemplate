package orz.kassy.androidtemplate;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

import orz.kassy.androidtemplate.data.ContactListItem;

public class ContactProviderActivity extends ListActivity
            implements LoaderManager.LoaderCallbacks<Cursor>{


    ListView mListView;
    // This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;


    // These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};

    // This is the select criteria
    static final String SELECTION = "((" +
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact_provider);
//        mListView = (ListView) findViewById(R.id.list_contact);
//        mListView.setEmptyView(findViewById(R.id.empty_contact));

//        Cursor cursor = getContentResolver().query(Data.CONTENT_URI,
//                                                   new String[] {Data._ID, Phone.NUMBER, Phone.TYPE, Phone.LABEL},
//                                                   Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "'",
//                                                   //new String[] {String.valueOf(contactId)},
//                                                   null,
//                                                   null);

        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1};
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        getLoaderManager().initLoader(0, null, this);
    }

    // Called when a new Loader needs to be created
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, ContactsContract.Data.CONTENT_URI,
                PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loadin
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    private class ContactListAdapter extends BaseAdapter {


        private final List<ContactListItem> mDataList;

        public ContactListAdapter(List<ContactListItem> dataList) {
            super();
            mDataList = dataList;
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.view_contact_list_row, null);
            }
            ContactListItem item = (ContactListItem) getItem(position);
            if(item == null) {
                return null;
            }
            ((TextView)convertView.findViewById(R.id.txtContactName)).setText(item.getName());
            return convertView;
        }
    }

}
