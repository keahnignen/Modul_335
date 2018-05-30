package kt.audiograph;

/**
 * @msg I like dicks in my butthole
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Record extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        addBadisToList();
    }

    @Override
    public void onClick(View v) {
        AudioDispatcher dispatcher =
                AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);
    }

    public void addBadisToList()
    {

        AdapterView.OnClickListener mListClickedHandler = new
                View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                };
        findViewById(R.id.btnSwag).setOnClickListener(this);

    }

}
