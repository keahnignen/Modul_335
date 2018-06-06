package k23r.audiograph2.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import k23r.audiograph2.R;

public class RecordFragment extends Fragment
{
    private boolean createdAndFirstTime = false;

    private LineGraphSeries<DataPoint> liveGraphSeries = new LineGraphSeries<DataPoint>();
    private List<Float> pointsForStore = new ArrayList<Float>();
    private Activity activity;

    public RecordFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        createdAndFirstTime = true;
        return(inflater.inflate(R.layout.record_fragment, container, false));
    }

    @Override
    public void onStart() {
        super.onStart();


        final Button button = getView().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StaticGraph.class);
                intent.putExtra("array", toArray(pointsForStore));
                startActivity(intent);
            }
        });

    }

    private float[] toArray(List<Float> swag)
    {
        float[] floats = new float[swag.size()];
        int i = 0;

        for (float f : swag)
        {
            floats[i] = f;
            i++;
        }
        return floats;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    private void setGraph()
    {
        GraphView graphView = (GraphView) getView().findViewById(R.id.graph);

        /*
        for (FrequencyPoint fp: rec.pointsForStore) {
            liveGraphSeries.appendData(new DataPoint(fp.date, fp.frequency), true, rec.pointsForStore.size(), true);
        }
        */

        /*
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {

            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " €";
                }
            }
        });
        */



        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(13);
        nf.setMinimumIntegerDigits(20);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));


/*
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

*/
        graphView.addSeries(liveGraphSeries);
    }

    public void addDatapoint(float frequency, boolean b, int i, boolean b1) {

        pointsForStore.add(frequency);
        liveGraphSeries.appendData(new DataPoint(new Date(), frequency), false, 50, false);

      if (createdAndFirstTime)
      {
          createdAndFirstTime = false;
          liveGraphSeries.setColor(Color.GREEN);
          setGraph();
          GraphView graphView = (GraphView) getView().findViewById(R.id.graph);
          graphView.addSeries(liveGraphSeries);
      }
    }


}
