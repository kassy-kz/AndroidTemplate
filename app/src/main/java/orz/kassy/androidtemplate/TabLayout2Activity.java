package orz.kassy.androidtemplate;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TabLayout2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout2);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab 2")
                .setIcon(R.drawable.ic_drawer));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab));

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter =
                new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.addAll(getColorList());
        tabLayout.setupWithViewPager(viewPager);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        /** 色情報リスト. */
        private ArrayList<SparseArrayCompat<String>> mList;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            mList = new ArrayList<SparseArrayCompat<String>>();
        }

        @Override public Fragment getItem(int position) {

            // 対象ページの色情報を取得
            SparseArrayCompat<String> item = mList.get(position);

            // 色情報を Bundle にする
            Bundle bundle = new Bundle();
            bundle.putInt("page", position);
            bundle.putString("color", item.get(0));
            bundle.putString("name", item.get(1));
            bundle.putString("description", item.get(2));

            // Fragment をつくり Bundle をセットする
            TraditionalColorFragment frag = new TraditionalColorFragment();
            frag.setArguments(bundle);
            return frag;
        }
        @Override public int getCount() {
            return 3;
        }
        @Override public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }

        /**
         * 色情報を追加する.
         * @param item 色情報
         */
        public void add(SparseArrayCompat<String> item) {
            mList.add(item);
        }

        /**
         * 色情報をリストで追加する.
         * @param list 色情報リスト
         */
        public void addAll(ArrayList<SparseArrayCompat<String>> list) {
            mList.addAll(list);
        }

    }

    /**
     * 色情報を表示する Fragment.
     */
    public class TraditionalColorFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // データを取得
            Bundle bundle = getArguments();
            int page = bundle.getInt("page");
            String color = bundle.getString("color");
            String name = bundle.getString("name");
            String description = bundle.getString("description");

            // View をつくる
            View layout = inflater.inflate(R.layout.fragment_traditional_color, container, false);
            layout.setBackgroundColor(Color.parseColor(color));

            // 色名
            TextView nameView = (TextView) layout.findViewById(R.id.name_view);
            nameView.setText(name);
            // 説明 (色名の由来)
            TextView textView = (TextView) layout.findViewById(R.id.text_view);
            textView.setText(description);
            // 色コード
            TextView colorView = (TextView) layout.findViewById(R.id.color_view);
            colorView.setText(color);
            // ページ
            TextView pageView = (TextView) layout.findViewById(R.id.page_view);
            pageView.setText(String.valueOf(page));

            return layout;
        }

    }

    /**
     * 色情報リストを返す.
     * @return 色情報リスト
     */
    private ArrayList<SparseArrayCompat<String>> getColorList() {
        ArrayList<SparseArrayCompat<String>> list = new ArrayList<SparseArrayCompat<String>>();

        SparseArrayCompat<String> color1 = new SparseArrayCompat<String>();
        color1.append(0, "#727171");
        color1.append(1, "鈍色 (にびいろ)");
        color1.append(2, "かすかに緑や茶の色味を持つグレイに近い色。");
        SparseArrayCompat<String> color2 = new SparseArrayCompat<String>();
        color2.append(0, "#2792c3");
        color2.append(1, "縹 (はなだ)");
        color2.append(2, "藍の単一染めの純正な青色。｢花田｣とも書く。");
        SparseArrayCompat<String> color3 = new SparseArrayCompat<String>();
        color3.append(0, "#917347");
        color3.append(1, "朽葉色 (くちばいろ)");
        color3.append(2, "朽ちた落ち葉の色に似た褐色の、黄橙色(「黄唐茶」)。｢朽葉四十八色｣と言う言葉がある。");
        SparseArrayCompat<String> color4 = new SparseArrayCompat<String>();
        color4.append(0, "#3a5b52");
        color4.append(1, "虫襖 (むしあお)");
        color4.append(2, "玉虫の羽根の色に見るような、暗い青みの緑。｢虫青｣とも書く。");
        SparseArrayCompat<String> color5 = new SparseArrayCompat<String>();
        color5.append(0, "#f8b500");
        color5.append(1, "山吹色 (やまぶきいろ)");
        color5.append(2, "山吹の花の色のような、冴えた赤味の黄色。｢黄金色｣とも言う。");
        SparseArrayCompat<String> color6 = new SparseArrayCompat<String>();
        color6.append(0, "#e5abbe");
        color6.append(1, "石竹色 (せきちくいろ)");
        color6.append(2, "ナデシコ科の植物セキチクの花のような淡い赤色。");
        SparseArrayCompat<String> color7 = new SparseArrayCompat<String>();
        color7.append(0, "#e60026");
        color7.append(1, "紅蓮 (ぐれん)");
        color7.append(2, "盛んに燃え上がる炎の色。「紅蓮地獄（寒さのために皮膚が裂け血が流れ、紅の蓮の花のようになる）」を「紅の炎の燃え立つ所」と誤認したのが由来。");
        SparseArrayCompat<String> color8 = new SparseArrayCompat<String>();
        color8.append(0, "#007b43");
        color8.append(1, "常磐色 (ときわいろ)");
        color8.append(2, "スギなどの常緑樹の葉のようなくすんだ緑色。");

        list.add(color1);
        list.add(color2);
        list.add(color3);
        list.add(color4);
        list.add(color5);
        list.add(color6);
        list.add(color7);
        list.add(color8);

        return list;
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
