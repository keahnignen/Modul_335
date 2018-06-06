package kenabis.myaudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        //  activate back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
