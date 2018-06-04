package k23r.audiograph2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.Series;

import k23r.audiograph2.R;

public class RecordFragment extends Fragment
{
    public boolean isCreated = false;

    public RecordFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        isCreated = true;
        return(inflater.inflate(R.layout.record_fragment, container, false));
    }

    public void SetGraph(Series series)
    {
        if (!isCreated) return;
        GraphView graphView = (GraphView) getView().findViewById(R.id.graph);

        /*
        for (FrequencyPoint fp: rec.swag) {
            series.appendData(new DataPoint(fp.date, fp.frequency), true, rec.swag.size(), true);
        }
        */


        graphView.addSeries(series);
    }
}
