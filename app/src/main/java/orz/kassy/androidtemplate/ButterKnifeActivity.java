package orz.kassy.androidtemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ButterKnifeActivity extends AppCompatActivity {


    @InjectView(R.id.textview_butter_knife_main)
    TextView mTextView;


    @OnClick(R.id.btn_butter_knife_main1)
    void onClickButton1() {
        mTextView.setText("button1 pressed");
    }

    @OnClick(R.id.btn_butter_knife_main2)
    void onClickButton2() {
        mTextView.setText("button2 pressed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);

        ButterKnife.inject(this);

    }

}
