package kenabis.myaudio.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kenabis.myaudio.R;
import kenabis.myaudio.record.audio.AudioRecorder;

public class RecordFragment extends Fragment
{
    private String name = "Record";
    private AudioRecorder recorder;

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private boolean isGranted = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_fragment, container, false);

        this.requestAudioPermissions();

        return(rootView);
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

    public void recordClick(View btn)
    {
        if (!this.isGranted)
        {
            requestAudioPermissions();
        }
        else
        {
            if (this.recorder.isRecording)
            {
                this.recorder.stopRecording();
            }
            else
            {
                this.recorder.startRecording();
            }
        }
    }

    //  fragment.addDatapoint(pitchInHz, false, 100, false);
}
