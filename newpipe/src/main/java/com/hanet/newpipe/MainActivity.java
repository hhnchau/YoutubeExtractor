package com.hanet.newpipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.kiosk.KioskInfo;
import org.schabi.newpipe.extractor.search.SearchInfo;
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfo;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void extract(View view) {

        Single.fromCallable(
                new Callable<StreamInfo>() {
                    @Override
                    public StreamInfo call() throws Exception {
                        return StreamInfo.getInfo(NewPipe.getService(0), "https://www.youtube.com/watch?v=ks_lorz0cik");
                    }
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<StreamInfo>() {
                    @Override
                    public void onSuccess(StreamInfo info) {
                        String s = "";
                        YoutubeStreamExtractor youtubeStreamExtractor;
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = "";
                        // http://youtube.com/get_video_info?video_id=ks_lorz0cik
                    }
                });


    }

    private void search(){
                Single.fromCallable(() ->
                SearchInfo.getInfo(NewPipe.getService(0),
                        NewPipe.getService(0)
                                .getSearchQHFactory()
                                .fromQuery("em", new ArrayList<>(), "")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<SearchInfo>() {
                    @Override
                    public void onSuccess(SearchInfo searchInfo) {
                        String s = "";
                        //getMoreSearch(searchInfo.getNextPage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = "";
                    }
                });
    }

    private void kiosk(){
        Single.fromCallable(
                new Callable<KioskInfo>() {
                    @Override
                    public KioskInfo call() throws Exception {
                        return KioskInfo.getInfo(NewPipe.getService(0), "https://www.youtube.com/feed/trending");
                    }
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<KioskInfo>() {
                    @Override
                    public void onSuccess(KioskInfo info) {
                        String s = "";
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = "";
                    }
                });
    }

    private void getMoreKiosk(Page page){
        Single.fromCallable(
                new Callable<ListExtractor.InfoItemsPage<StreamInfoItem>>() {
                    @Override
                    public ListExtractor.InfoItemsPage<StreamInfoItem> call() throws Exception {
                        return KioskInfo.getMoreItems(NewPipe.getService(0), "https://www.youtube.com/feed/trending", page);
                    }
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ListExtractor.InfoItemsPage<StreamInfoItem>>() {

                    @Override
                    public void onSuccess(ListExtractor.InfoItemsPage<StreamInfoItem> streamInfoItemInfoItemsPage) {
                        String s = "";
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = "";
                    }
                });

    }

    private void getMoreSearch(Page page){
        Single.fromCallable(() ->
                SearchInfo.getMoreItems(NewPipe.getService(0),
                        NewPipe.getService(0)
                                .getSearchQHFactory()
                                .fromQuery("hoai linh", new ArrayList<>(), ""), page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ListExtractor.InfoItemsPage<InfoItem>>() {
                    @Override
                    public void onSuccess(ListExtractor.InfoItemsPage<InfoItem> infoItemInfoItemsPage) {
                        String s = "";
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = "";
                    }
                })
        ;
    }

}
