package k23r.audiograph2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import k23r.audiograph2.R;

public class CollectionFragment extends Fragment
{
    public CollectionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return(inflater.inflate(R.layout.collection_fragment, container, false));
    }
}
