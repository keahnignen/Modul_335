package k23r.audiograph2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import k23r.audiograph2.Record.Record;
import k23r.audiograph2.fragments.RecordFragment;
import k23r.audiograph2.fragments.CollectionFragment;

public class LauncherActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.launcher_activity);

        viewPager = findViewById(R.id.viewpager);
        setupFragmentPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Record record = new Record(this);
    }

    private void setupFragmentPager(ViewPager viewPager)
    {
        FragmentPager adapter = new FragmentPager(getSupportFragmentManager());

        adapter.addFragment(new RecordFragment(), "Record");
        adapter.addFragment(new CollectionFragment(), "Collection");

        viewPager.setAdapter(adapter);
    }
}



