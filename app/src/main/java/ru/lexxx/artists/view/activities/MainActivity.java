package ru.lexxx.artists.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.lexxx.artists.R;
import ru.lexxx.artists.interfaces.IPresenter;
import ru.lexxx.artists.interfaces.IView;
import ru.lexxx.artists.model.Artist;
import ru.lexxx.artists.presenter.Presenter;
import ru.lexxx.artists.utils.Constants;
import ru.lexxx.artists.view.adapters.ArtistsAdapter;

/**
 * Главное Activity.
 */
public class MainActivity extends AppCompatActivity implements
        IView,
        AdapterView.OnItemClickListener {
    private IPresenter presenter;
    private ArtistsAdapter adapter;

    // виджеты экрана
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.state)
    TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // связываем поля с аннотацией @Bind с текущей активити
        ButterKnife.bind(this);

        // настраиваем action bar
        setSupportActionBar(toolbar);

        // устанавливаем обработчик нажатия на элемент списка
        list.setOnItemClickListener(this);

        // создаем новый объект Presenter
        presenter = new Presenter(getApplicationContext(), this);
        // загружаем список артистов
        presenter.getArtists();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // отписываемся
        if (presenter != null)
            presenter.onStop();
    }

    @Override
    public void showData(List<Artist> data) {
        // создаем адаптер и прикрепляем его к списку
        if (adapter == null) {
            adapter = new ArtistsAdapter(getApplicationContext(), data);
            list.setAdapter(adapter);
        }
    }

    @Override
    public void showError(String error) {
        // показывает Toast с ошибкой
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyData() {
        showState(getString(R.string.state_no_items), true);
    }

    @Override
    public void showProgress(boolean show) {
        if (show)
            progress.setVisibility(View.VISIBLE);
        else
            progress.setVisibility(View.GONE);
    }

    @Override
    public void showState(String text, boolean show) {
        if (TextUtils.isEmpty(text)) {
            state.setVisibility(View.GONE);
            return;
        }

        if (show) {
            state.setText(text);
            state.setVisibility(View.VISIBLE);
        }
        else
            state.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // создаем Intent и на основе него запускаем Activity
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        intent.putExtra(Constants.EXTRA_ARTIST, (Artist)adapter.getItem(position));
        startActivity(intent);
    }
}