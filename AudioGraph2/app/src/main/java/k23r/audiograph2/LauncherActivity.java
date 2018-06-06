package k23r.audiograph2;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import k23r.audiograph2.Record.Record;
import k23r.audiograph2.Record.RecordView;
import k23r.audiograph2.fragments.RecordFragment;
import k23r.audiograph2.fragments.CollectionFragment;

public class LauncherActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecordFragment fragment;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.launcher_activity);




        viewPager = findViewById(R.id.viewpager);
        setupFragmentPager(viewPager);


        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        View view = this.findViewById(R.id.graph);
        Record record = new Record(fragment, this);

        //recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);

        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);
        //recycleViewAdapter = new RecycleViewAdapter();

        //Record record = new Record(fragment, this);

    }



    private void setupFragmentPager(ViewPager viewPager)
    {
        FragmentPager adapter = new FragmentPager(getSupportFragmentManager());

        fragment = new RecordFragment();

        adapter.addFragment(fragment, "Record");
        adapter.addFragment(new CollectionFragment(), "Collection");

        viewPager.setAdapter(adapter);
    }
}



