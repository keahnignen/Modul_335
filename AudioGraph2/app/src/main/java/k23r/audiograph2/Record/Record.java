package k23r.audiograph2.Record;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import k23r.audiograph2.R;

public class Record {

    private Activity a;
    private Recordation rec;

    public Record(Activity a)
    {
        this.a = a;
        rec = new Recordation();
        display();
        AudioPermission ap = new AudioPermission(a, this);


    }


    void StraitCopyied() {
        Toast.makeText(a, "Swag2", Toast.LENGTH_LONG).show();
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e) {
                final float pitchInHz = res.getPitch();
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        processPitch(pitchInHz);
                    }
                });
            }
        };

        AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);


        Thread audioThread = new Thread(dispatcher, "Audio Thread");
        Toast.makeText(a, "Swag", Toast.LENGTH_LONG).show();
        audioThread.start();

    }


    Date d = new Date();

    private void processPitch(float pitchInHz) {
<<<<<<< HEAD
        //TextView pitchText = (TextView) a.findViewById(R.id.lblFrequency);
        //TextView noteText = (TextView) a.findViewById(R.id.lblNote);

        //pitchText.setText("" + pitchInHz);
        Date d = new Date();
        rec.swag.put(d.toString(), pitchInHz);
=======
        
        index++;
        Toast.makeText(a, ""+pitchInHz, Toast.LENGTH_LONG).show();
        series.appendData(new DataPoint(new Date(), pitchInHz), false, 100, false);
        //rec.swag.add(new FrequencyPoint(new Date(), pitchInHz));
        //bizzli.add(pitchInHz);
>>>>>>> 4463789065e9913dc0648c46607c513825f1e959
    }

    private List<Float> bizzli =  new ArrayList<>();

    private int index= 0;

    private LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

    private void display() {
        GraphView graph = (GraphView) a.findViewById(R.id.graph);

        /*
        for (FrequencyPoint fp: rec.swag) {
            series.appendData(new DataPoint(fp.date, fp.frequency), true, rec.swag.size(), true);
        }
        */


        graph.addSeries(series);
    }


}
