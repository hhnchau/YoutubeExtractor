package m.tube.youtubeextrator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import m.tube.youtubeextrator.model.YTMedia;
import m.tube.youtubeextrator.model.YTSubtitles;
import m.tube.youtubeextrator.model.YoutubeMeta;
import m.tube.youtubeextrator.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void extract(View view) {

        Toast.makeText(getApplicationContext(), "Extracting", Toast.LENGTH_LONG).show();

        new YoutubeStreamExtractor(new YoutubeStreamExtractor.ExtractorListner(){

            @Override
            public void onExtractionDone(List<YTMedia> adativeStream, final List<YTMedia> muxedStream, List<YTSubtitles> subtitles, YoutubeMeta meta) {

                //url to get subtitle
                //String subUrl=subtitles.get(0).getBaseUrl();

                for (YTMedia c:muxedStream) {
                    LogUtils.log("null ha");
                }
                for (YTMedia media:adativeStream) {
                    LogUtils.log("null ha");
                }
                //Toast.makeText(getApplicationContext(), meta.getTitle(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), meta.getAuthor(), Toast.LENGTH_LONG).show();
                if (adativeStream.isEmpty()) {
                    LogUtils.log("null ha");
                    return;
                }
                if (muxedStream.isEmpty()) {
                    LogUtils.log("null ha");
                    return;
                }
                String url = muxedStream.get(0).getUrl();

            }


            @Override
            public void onExtractionGoesWrong(final ExtractorException e) {

                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();


            }
        }).useDefaultLogin().Extract("https://youtu.be/ks_lorz0cik");
    }
}
