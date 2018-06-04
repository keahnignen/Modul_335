package k23r.audiograph2.Record;

import android.app.Activity;
import android.widget.Toast;

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
import k23r.audiograph2.fragments.RecordFragment;

public class Record {

    private RecordFragment fragment;
    private Activity activity;
    private Recordation rec;

    public Record(RecordFragment a, Activity ac)
    {
        this.activity = ac;
        this.fragment = a;
        rec = new Recordation();
        display();
        AudioPermission ap = new AudioPermission(ac, this);


    }


    void StraitCopyied() {
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e) {
                final float pitchInHz = res.getPitch();
                activity.runOnUiThread(new Runnable() {
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
        audioThread.start();

    }


    Date d = new Date();

    private void processPitch(float pitchInHz) {
        //TextView pitchText = (TextView) fragment.findViewById(R.id.lblFrequency);
        //TextView noteText = (TextView) fragment.findViewById(R.id.lblNote);

        //pitchText.setText("" + pitchInHz);
        Date d = new Date();
        //rec.swag.put(d.toString(), pitchInHz);
        index++;
        series.appendData(new DataPoint(new Date(), pitchInHz), false, 100, false);
        //rec.swag.add(new FrequencyPoint(new Date(), pitchInHz));
        //bizzli.add(pitchInHz);
    }

    private List<Float> bizzli =  new ArrayList<>();

    private int index= 0;

    private LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

    private void display() {
        fragment.SetGraph(series);
    }




}
