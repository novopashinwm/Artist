package ru.lexxx.artists.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

import retrofit.Response;
import ru.lexxx.artists.interfaces.IModel;
import ru.lexxx.artists.model.api.ApiFactory;
import ru.lexxx.artists.model.api.ApiInterface;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Реализует интерфейс {@link IModel}.
 */
public class Model implements IModel {
    @Override
    public Observable<List<Artist>> getArtists(Context context) {
        return ApiFactory.getApiInterface().getArtists()
                .subscribeOn(Schedulers.io()) // данные будут загружаться в отдельном потоке
                .observeOn(AndroidSchedulers.mainThread()); // а отображаться в главном потоке
    }
}
