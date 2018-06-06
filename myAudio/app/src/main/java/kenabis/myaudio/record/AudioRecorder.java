package kenabis.myaudio.record;

import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecorder
{
    private AudioRecord recorder;
    private Thread recordingThread;
    public boolean isRecording = false;

    private final int RECORDER_RATE = 44100;
    private final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private final String AUDIO_OUTPUT_PATH = "file:///android_asset/sounds/";

    private final int BufferElements2Rec = 1024;
    private final int BytesPerElement = 2;

    public AudioRecorder()
    {

    }

    public byte[] shortToByte(short[] data)
    {
        int shortArraySize = data.length;
        byte[] bytes = new byte[shortArraySize * 2];

        for (int i = 0; i < shortArraySize; i++)
        {
            bytes[i * 2] = (byte) (data[i] & 0x00FF);
            bytes[(i * 2) + 1] = (byte) (data[i] >> 8);
            data[i] = 0;
        }

        return(bytes);
    }

    public void writeAudioToFile()
    {
        //  write audio data to file
        short[] data = new short[this.BufferElements2Rec];

        try
        {
            FileOutputStream os = new FileOutputStream(this.AUDIO_OUTPUT_PATH + "");

            while(this.isRecording)
            {
                this.recorder.read(data, 0, this.BufferElements2Rec);

                byte[] byteData = shortToByte(data);

                os.write(byteData, 0, (this.BufferElements2Rec * this.BytesPerElement));
                os.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void startRecording()
    {
        this.recorder = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                this.RECORDER_RATE,
                this.RECORDER_CHANNELS,
                this.RECORDER_AUDIO_ENCODING,
                (this.BufferElements2Rec * this.BytesPerElement)
        );

        this.recorder.startRecording();
        this.isRecording = true;

        this.recordingThread = new Thread(new Runnable() {
            public void run()
            {
                writeAudioToFile();
            }
        }, "AudioRecorder Thread");

        this.recordingThread.start();
    }

    public void stopRecording()
    {
        if (this.recorder != null)
        {
            this.isRecording = false;
            this.recorder.stop();
            this.recorder.release();
            this.recorder = null;
            this.recordingThread = null;
        }
    }
}
