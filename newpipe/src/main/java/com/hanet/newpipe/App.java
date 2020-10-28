package com.hanet.newpipe;

import android.app.Application;

import com.hanet.newpipe.utils.PipeLocalization;

import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.downloader.Downloader;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        NewPipe.init(getDownloader(),
                PipeLocalization.getPreferredLocalization(this),
                PipeLocalization.getPreferredContentCountry(this));

    }

    protected Downloader getDownloader() {
        final DownloaderImpl downloader = DownloaderImpl.init(null);
        return downloader;
    }

}
