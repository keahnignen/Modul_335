package fg.badoapp.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class BadiDetailsActivity extends AppCompatActivity {

    private int bathId;
    private ProgressBar progressBar;

    private final static String WIE_WARM_API_URL = "http://www.wiewarm.ch/api/v1/bad.json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_details);
    }
}
