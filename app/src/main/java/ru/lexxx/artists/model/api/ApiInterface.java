package ru.lexxx.artists.model.api;

import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Response;
import retrofit.http.GET;
import ru.lexxx.artists.model.Artist;
import rx.Observable;

/**
 * Описывает интерфес для вызова методов API.
 */
public interface ApiInterface {
    /**
     * Загружает список артистов.
     * @return
     */
    @GET("artists.json")
    Observable<List<Artist>> getArtists();
}
