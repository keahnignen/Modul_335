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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

import k23r.audiograph2.R;

public class RecordFragment extends Fragment
{
    private boolean createtAndFirstTime = false;

    private Activity a;
    private OtherBLa b;


    public RecordFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        createtAndFirstTime = true;




        return(inflater.inflate(R.layout.record_fragment, container, false));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button button = getView().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OtherBLa.class);
                startActivity(intent);
            }
        });
    }

    public LineGraphSeries<DataPoint> badSeries = new LineGraphSeries<DataPoint>();
    public LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();


    public void SetGraph()
    {
        GraphView graphView = (GraphView) getView().findViewById(R.id.graph);

        /*
        for (FrequencyPoint fp: rec.swag) {
            series.appendData(new DataPoint(fp.date, fp.frequency), true, rec.swag.size(), true);
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


         /*
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(13);
        nf.setMinimumIntegerDigits(20);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
*/

/*
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

*/
        graphView.addSeries(series);
    }

    public void addDatapoint(float frequency, boolean b, int i, boolean b1) {


        if (frequency < 1000 && frequency > 500 )
        {
            series.appendData(new DataPoint(new Date(), frequency), false, 50, false);

        }
        else
        {
            badSeries.appendData(new DataPoint(new Date(), frequency), false, 100, false);
        }



      if (createtAndFirstTime)
      {
          createtAndFirstTime = false;
          //View v = this.getView().findViewById(R.id.view);

          //series.setDrawBackground(true);
          //badSeries.setDrawBackground(true);
          badSeries.setColor(Color.RED);
          series.setColor(Color.GREEN);
          SetGraph();
          GraphView graphView = (GraphView) getView().findViewById(R.id.graph);
          graphView.addSeries(series);
          graphView.addSeries(badSeries);
      }
    }


}
