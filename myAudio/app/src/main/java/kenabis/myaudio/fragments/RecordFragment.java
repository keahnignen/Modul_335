package kenabis.myaudio.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kenabis.myaudio.R;
import kenabis.myaudio.record.audio.AudioRecorder;
import kenabis.myaudio.record.graph.Record;
import kenabis.myaudio.StaticGraph;

public class RecordFragment extends Fragment
{
    private boolean createdAndFirstTime = false;
    private String name = "Record";
    private AudioRecorder recorder;

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private boolean isGranted = false;

    private Record record;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_fragment, container, false);
        this.requestAudioPermissions();
        createdAndFirstTime = true;
        return(rootView);
    }


    private float[] toArray(List<Float> list)
    {
        float[] floats = new float[list.size()];
        int i = 0;

        for (float f : list)
        {
            floats[i] = f;
            i++;
        }
        return floats;
    }

    public String getName()
    {
        return(this.name);
    }

    private void requestAudioPermissions()
    {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.RECORD_AUDIO))
            {
                Toast
                    .makeText(getContext(), "Please allow myAudio to use your microphone", Toast.LENGTH_LONG)
                    .show()
                ;

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
            }
            else
            {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
            }
        }
        else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        {
            //  permission granted, record now
            this.record = new Record(this, getActivity());
            this.recorder = new AudioRecorder();
            this.isGranted = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_RECORD_AUDIO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //  permission granted, record now
                    this.record = new Record(this,  getActivity());
                    this.recorder = new AudioRecorder();
                    this.isGranted = true;
                }
                else
                {
                    Toast
                        .makeText(getContext(), "Permissions Denied to record audio", Toast.LENGTH_LONG)
                        .show()
                    ;
                }

                return;
        }
    }


    private boolean startet = false;

    public void recordClick(View btn)
    {
        if (!this.isGranted)
        {
            requestAudioPermissions();
        }
        else
        {
            if (startet)
            {
                startet = !startet;
                this.recorder.stopRecording();
                Intent intent = new Intent(getActivity(), StaticGraph.class);
                intent.putExtra("array", toArray(pointsForStore));
                startActivity(intent);
            }
            else
            {
                startet = !startet;
                this.record.startRecord();
                this.recorder.startRecording();


            }
        }
    }

    private LineGraphSeries<DataPoint> liveGraphSeries = new LineGraphSeries<DataPoint>();
    private List<Float> pointsForStore = new ArrayList<Float>();


    private void setGraph()
    {
        GraphView graphView = (GraphView) getView().findViewById(R.id.graph);

        /*
        for (FrequencyPoint fp: rec.pointsForStore) {
            liveGraphSeries.appendData(new DataPoint(fp.date, fp.frequency), true, rec.pointsForStore.size(), true);
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
                    return super.formatLabel(value, isValueX) + " Hz";
                }
            }
        });



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
