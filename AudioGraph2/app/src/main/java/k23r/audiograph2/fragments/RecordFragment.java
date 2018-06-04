package k23r.audiograph2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import k23r.audiograph2.R;

public class RecordFragment extends Fragment
{
    private boolean createtAndFirstTime = false;

    public RecordFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        createtAndFirstTime = true;
        return(inflater.inflate(R.layout.record_fragment, container, false));
    }

    public LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();


    public void SetGraph()
    {
        GraphView graphView = (GraphView) getView().findViewById(R.id.graph);

        /*
        for (FrequencyPoint fp: rec.swag) {
            series.appendData(new DataPoint(fp.date, fp.frequency), true, rec.swag.size(), true);
        }
        */

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


         /*
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(13);
        nf.setMinimumIntegerDigits(20);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
*/


        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graphView.addSeries(series);
    }

    public void addDatapoint(DataPoint dataPoint, boolean b, int i, boolean b1) {
      series.appendData(dataPoint, b, i, b1);

      if (createtAndFirstTime)
      {
          createtAndFirstTime = false;
          SetGraph();
          GraphView graphView = (GraphView) getView().findViewById(R.id.graph);
          graphView.addSeries(series);
      }
    }
}
