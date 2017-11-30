package muhavani.com.project_exempt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class event_detail extends AppCompatActivity {

    private WebView mwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        //1
        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");

        //2
        setTitle(title);

        //3
        mwebview = (WebView) findViewById(R.id.detail_web_view);

        //4
        mwebview.loadUrl(url);
    }
}
