package ru.lexxx.artists.presenter;

import android.content.Context;
import android.view.View;

import java.util.List;

import ru.lexxx.artists.interfaces.IModel;
import ru.lexxx.artists.interfaces.IPresenter;
import ru.lexxx.artists.interfaces.IView;
import ru.lexxx.artists.model.Artist;
import ru.lexxx.artists.model.Model;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Реализация интерфейса {@link IPresenter}.
 */
public class Presenter implements IPresenter {
    private Context context;
    private IModel model = new Model();
    private IView view;
    private Subscription subscription = Subscriptions.empty();

    /**
     * Конструктор.
     * @param context контекст.
     * @param view объект, реализующий интерфейс {@link IView}
     */
    public Presenter(Context context, IView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getArtists() {
        // отписываемся от получения уведомлений
        if (!subscription.isUnsubscribed())
            subscription.unsubscribe();

        // скрываем надпись с текущим состоянием
        view.showState("", false);
        // показываем ProgressBar
        view.showProgress(true);

        // создаем подписку
        subscription = model.getArtists(context).subscribe(new Observer<List<Artist>>() {
            @Override
            public void onCompleted() {
                // по завершению отправки уведомления подписчику скрываем ProgressBar
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                // при возникновении ошибки, показываем надпись с описанием ошибки
                view.showError(e.getMessage());

                // скрываем ProgressBar
                view.showProgress(false);
            }

            @Override
            public void onNext(List<Artist> artists) {
                // если список непустой, отображаем его
                if (artists != null && !artists.isEmpty())
                    view.showData(artists);
                else // иначе показываем надпись "Нет элементов."
                    view.showEmptyData();
            }
        });
    }

    @Override
    public void onStop() {
        // отписываемся от получения уведомлений
        if (!subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
