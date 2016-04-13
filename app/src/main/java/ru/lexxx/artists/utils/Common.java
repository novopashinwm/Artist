package ru.lexxx.artists.utils;

import android.content.Context;

import ru.lexxx.artists.R;

/**
 * Утилиты.
 */
public class Common {
    /**
     * Формирует строку со статистикой.
     * @param context контекст.
     * @param tracks кол-во треков.
     * @param albums кол-во альбомов.
     * @param type тип статистики.
     * @return
     */
    public static String formatStatistics(Context context, int tracks, int albums, int type) {
        // Логика работы:
        // меняем склонение сущуствительного в зависимости от кол-ва
        // для примера просклоняем существительное альбом, получим такой список
        // 0 альбомов
        // 1 альбом
        // 2 альбома
        // 2 альбома
        // 4 альбома
        // 5 альбомов
        // ...
        // 19 альбомов
        // получается, что для интервала чисел [0; 20] существует три склонения для слова, далее ситуация повторяется
        // интервалы для склонений
        // [0], [5; 19] - альбомов
        // [1] - альбом
        // [2; 4] - альбома
        // таким образом для каждого числа необходимо брать последние две цифры, и определять к какому интервалу отностися полученное число
        // если полученное число больше 20, то берется последняя цифра этого числа и также определяется, к какому интервалу она относится

        StringBuilder stringBuilder = new StringBuilder();

        int last = albums;

        if (albums >= 20)
            last = albums % 100;

        if (last >= 20)
            last = last % 10;

        if ((last >= 5 && last <= 19) || last == 0)
            stringBuilder.append(String.format(context.getString(R.string.title_albums_3), albums));
        else if (last >= 2 && last <= 4)
            stringBuilder.append(String.format(context.getString(R.string.title_albums_2), albums));
        else if (last == 1)
            stringBuilder.append(String.format(context.getString(R.string.title_albums_1), albums));

        if (stringBuilder.length() > 0) {
            switch (type) {
                case Constants.STATISTICS_TYPE_LIST_ITEM:
                    stringBuilder.append(", ");
                    break;

                case Constants.STATISTICS_TYPE_INFO:
                    stringBuilder.append(" \u00b7 ");
                    break;
            }
        }

        last = tracks;

        if (tracks >= 20)
            last = tracks % 100;

        if (last >= 20)
            last = last % 10;

        if ((last >= 5 && last <= 19) || last == 0)
            stringBuilder.append(String.format(context.getString(R.string.title_tracks_3), tracks));
        else if (last >= 2 && last <= 4)
            stringBuilder.append(String.format(context.getString(R.string.title_tracks_2), tracks));
        else if (last == 1)
            stringBuilder.append(String.format(context.getString(R.string.title_tracks_1), tracks));

        return stringBuilder.toString();
    }
}
