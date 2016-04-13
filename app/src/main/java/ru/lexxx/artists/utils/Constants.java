package ru.lexxx.artists.utils;

import ru.lexxx.artists.BuildConfig;

/**
 * Константы.
 */
public class Constants {
    // Базовый URL API.
    public final static String BASE_URL = "http://download.cdn.yandex.net/mobilization-2016/";

    /**
     * Ключ для данных типа {@link ru.lexxx.artists.model.Artist}
     */
    public final static String EXTRA_ARTIST = BuildConfig.APPLICATION_ID + ".Artist";

    // Тип статистики:
    // в элементе списка
    public final static int STATISTICS_TYPE_LIST_ITEM = 0;
    // на экране информации о исполнителе
    public final static int STATISTICS_TYPE_INFO = 1;
}
