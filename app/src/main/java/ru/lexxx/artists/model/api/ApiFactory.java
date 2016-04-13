package ru.lexxx.artists.model.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import ru.lexxx.artists.utils.Constants;

/**
 * Содержит функционал, который создает интерфейс для вызова методов API.
 */
public class ApiFactory {
    // http клиент
    private static OkHttpClient okHttpClient;

    /**
     * Создает интерфейс для вызова методов API.
     * @return возвращает реализацию {@link ApiInterface}.
     */
    public static ApiInterface getApiInterface() {
        // создаем http клиент
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
            // таймацт на соединение
            okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
            // таймацт на чтение
            okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL) // базовый URL API.
                .client(okHttpClient) // http клиент.
                .addConverterFactory(GsonConverterFactory.create()) // конвертер передаваемых объектов.
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()); //

        return builder.build().create(ApiInterface.class);
    }
}
