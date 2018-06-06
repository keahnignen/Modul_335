package kenabis.myaudio.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kenabis.myaudio.PlayActivity;
import kenabis.myaudio.R;
import kenabis.myaudio.adapter.RecyclerAdapter;
import kenabis.myaudio.record.audio.Record;

public class GalleryFragment extends Fragment
{
    private String name = "Gallery";
    private List<Record> records = new ArrayList<>();

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //  get view
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment, container, false);

        //  get RecyclerView from view
        this.recycler = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        //  initialize records
        this.initializeData();

        //  set LayoutManager and Adapter
        this.manager = new LinearLayoutManager(getContext());
        this.adapter = new RecyclerAdapter(this.records);

        this.recycler.setLayoutManager(this.manager);
        this.recycler.setAdapter(this.adapter);
        this.recycler.setHasFixedSize(true);

        //  return cardItem
        return(rootView);
    }

    public String getName()
    {
        return(this.name);
    }

    public void initializeData()
    {
        //  example (hard-coded) may load from storage sometime
        this.records.add(new Record("Record one", "21.02.2001"));
        this.records.add(new Record("Record two", "21.02.2001"));
        this.records.add(new Record("Record three", "21.02.2001"));
    }

    public void deleteClick(View btn)
    {
        //  remove record
    }

    public void playClick(View btn)
    {
        Intent playActivity = new Intent(getActivity(), PlayActivity.class);
        getActivity().startActivity(playActivity);
    }
}
