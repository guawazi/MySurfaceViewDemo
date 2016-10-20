package orange.com.mysurfaceviewdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    private VideoView vedioView;
    private MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vedioView = (VideoView) findViewById(R.id.videoView);
        vedioView.setVideoURI(Uri.parse("http://qiubai.hdllive.ks-cdn.com/live/73748234547244692.flv"));
        mController = new MediaController(this);
        mController.show(5000);
        vedioView.setMediaController(mController);
        vedioView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        vedioView.requestFocus();
    }
}
