package m.tube.yt_extractor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.github.kotvertolet.youtubejextractor.JExtractorCallback;
import com.github.kotvertolet.youtubejextractor.YoutubeJExtractor;
import com.github.kotvertolet.youtubejextractor.exception.ExtractionException;
import com.github.kotvertolet.youtubejextractor.exception.YoutubeRequestException;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.YoutubeVideoData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
                extract();
    }

    public void extract() {

        YoutubeJExtractor youtubeJExtractor = new YoutubeJExtractor();
        youtubeJExtractor.extract("QHGbVHB7Y4c", new JExtractorCallback() {
            @Override
            public void onSuccess(YoutubeVideoData videoData) {
                // use extracted data
                String s = "";
            }

            @Override
            public void onNetworkException(YoutubeRequestException e) {
                // may be a connection problem, ask user to check his internet connection
                String s = "";
            }

            @Override
            public void onError(Exception exception) {
                // some serious problem occured, just show some error message
                String s = "";
            }
        });
    }
}
