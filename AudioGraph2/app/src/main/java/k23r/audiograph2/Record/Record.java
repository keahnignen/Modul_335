package k23r.audiograph2.Record;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

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
        AudioPermission ap = new AudioPermission(a, this);
        rec = new Recordation();
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
        TextView pitchText = (TextView) a.findViewById(R.id.lblFrequency);
        TextView noteText = (TextView) a.findViewById(R.id.lblNote);

        pitchText.setText("" + pitchInHz);
        Date d = new Date();
        rec.swag.put(d.toString(), pitchInHz);
    }



}
