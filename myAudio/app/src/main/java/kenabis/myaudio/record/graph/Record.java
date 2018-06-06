package kenabis.myaudio.record.graph;

import android.app.Activity;

import java.util.Date;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import kenabis.myaudio.fragments.RecordFragment;

public class Record {

    private RecordFragment fragment;
    private Activity activity;
    private Recordation rec;

    public Record(RecordFragment fragment, Activity activity)
    {
        this.activity = activity;
        this.fragment = fragment;
        rec = new Recordation();
        AudioPermission audioPermission = new AudioPermission(activity, this);
    }


    public void startRecord() {
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

    private void processPitch(float pitchInHz) {
        //TextView pitchText = (TextView) fragment.findViewById(R.id.lblFrequency);
        //TextView noteText = (TextView) fragment.findViewById(R.id.lblNote);

        //pitchText.setText("" + pitchInHz);
        Date d = new Date();
        //rec.swag.put(d.toString(), pitchInHz);
        //  fragment.addDatapoint(pitchInHz, false, 100, false);
        //rec.swag.add(new FrequencyPoint(new Date(), pitchInHz));
        //bizzli.add(pitchInHz);
    }

}
