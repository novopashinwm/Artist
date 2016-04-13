package ru.lexxx.artists.interfaces;

/**
 * Интерфейс описывающий представление.
 */
public interface IPresenter {
    /**
     * Загружает список артистов и отдает их подписчику.
     */
    void getArtists();

    /**
     * Останавливает получение уведомлений подписчиком.
     */
    void onStop();
}
