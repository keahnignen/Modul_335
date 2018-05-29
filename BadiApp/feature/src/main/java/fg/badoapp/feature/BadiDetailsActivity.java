package fg.badoapp.feature;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONException;

import fg.badoapp.feature.model.Bath;
import fg.badoapp.feature.model.WieWarmJsonParser;

public class BadiDetailsActivity extends AppCompatActivity {

    private int bathId;
    private ProgressBar progressBar;

    private final static String WIE_WARM_API_URL = "http://www.wiewarm.ch/api/v1/bad.json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_details);
        progressBar = findViewById(R.id.loading_badi_details_progress);
        Intent intent = getIntent();
        bathId = intent.getIntExtra("badiId", 0);
        String name = intent.getStringExtra("badiName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        getBadiTemp(WIE_WARM_API_URL + bathId);

    }

    private void getBadiTemp(String url)
    {
        final ArrayAdapter<Bath> beckenInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(DownloadManager.Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Bath badi = WieWarmJsonParser.createBadiFromJsonString(response);
                            beckenInfosAdapter.addAll(badi.getPools());
                            ListView badiInfoList = findViewById(R.id.becken_infos);
                            badiInfoList.setAdapter(beckenInfosAdapter);
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            generateAlertDialog();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }

}
