package orz.kassy.androidtemplate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureActivity;

public class QrCaptureActivity extends CaptureActivity {

    private final static int REQUEST_SCAN = 1;

    public QrCaptureActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_capture);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(),
                        QrCaptureCameraActivity.class);
                startActivityForResult(intent, REQUEST_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_SCAN:
                if (resultCode == RESULT_OK) {
                    TextView textView = ((TextView) findViewById(R.id.text));
                    textView.setText(data.getDataString());
                }
                break;
        }
    }
}
