package kenabis.myaudio;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kenabis.myaudio.adapter.FragmentAdapter;
import kenabis.myaudio.fragments.GalleryFragment;
import kenabis.myaudio.fragments.RecordFragment;

public class LauncherActivity extends AppCompatActivity
{
    private RecordFragment record;
    private GalleryFragment gallery;

    private ViewPager pager;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);

        //  get ViewPager and PagerAdapter
        this.pager = (ViewPager) findViewById(R.id.viewPager);
        this.adapter = new FragmentAdapter(getSupportFragmentManager());

        //  add Fragments and titles to adapter
        this.record = new RecordFragment();
        this.gallery = new GalleryFragment();

        this.adapter.addFragment(this.record);
        this.adapter.addTitle(this.record.getName());
        this.adapter.addFragment(this.gallery);
        this.adapter.addTitle(this.gallery.getName());

        //  add adapter to pager
        this.pager.setAdapter((PagerAdapter) this.adapter);

        //  setup TabLayout with pager
        TabLayout tablayout = (TabLayout) findViewById(R.id.tabLayout);
        tablayout.setupWithViewPager(this.pager);
    }

    @Override
    public void onBackPressed()
    {
        //  may load saved state.
    }

    public void playClick(View btn)
    {
        gallery.playClick(btn);
    }

    public void deleteClick(View btn)
    {
        gallery.deleteClick(btn);
    }

    public void recordClick(View btn)
    {
        record.recordClick(btn);
    }
}
