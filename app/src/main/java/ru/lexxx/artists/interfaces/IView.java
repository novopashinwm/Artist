package ru.lexxx.artists.interfaces;

import java.util.List;

import ru.lexxx.artists.model.Artist;

/**
 * Интерфейс описывающий вид.
 */
public interface IView {
    /**
     * Отображает данные в виде списка.
     * @param data список объектов типа {@link Artist}.
     */
    void showData(List<Artist> data);

    /**
     * Отображает ошибку в виде тоста.
     * @param error строка с ошибкой.
     */
    void showError(String error);

    /**
     * Отображает отсутствие данных.
     */
    void showEmptyData();

    /**
     * Показывает/скрывает ProgressBar.
     * @param show флаг отображения ProgressBar; true - показать, false - скрыть.
     */
    void showProgress(boolean show);

    /**
     * Показывает/скрывает TextView с определенным текстом.
     * @param text текст.
     * @param show флаг отображения TextView; true - показать, false - скрыть.
     */
    void showState(String text, boolean show);
}
