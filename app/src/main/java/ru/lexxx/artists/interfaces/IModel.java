package ru.lexxx.artists.interfaces;

import android.content.Context;

import java.util.List;

import ru.lexxx.artists.model.Artist;
import rx.Observable;

/**
 * Интерфейс описывающий модель.
 */
public interface IModel {
    /**
     * Загружает список артистов.
     * @param context контекст.
     * @return
     */
    Observable<List<Artist>> getArtists(Context context);
}
