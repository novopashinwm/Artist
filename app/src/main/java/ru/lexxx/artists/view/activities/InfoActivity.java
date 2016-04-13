package ru.lexxx.artists.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.lexxx.artists.R;
import ru.lexxx.artists.model.Artist;
import ru.lexxx.artists.utils.Common;
import ru.lexxx.artists.utils.Constants;

/**
 * Activity с информацией об артисте.
 */
public class InfoActivity extends AppCompatActivity {
    private Artist artist;

    // виджеты экрана
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.scroll)
    ScrollView scroll;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.genres)
    TextView genres;
    @Bind(R.id.statistics)
    TextView statistics;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);

        // связываем поля с аннотацией @Bind с текущей активити
        ButterKnife.bind(this);

        // настраиваем action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        progress.setVisibility(View.GONE);

        // получаем данные из intent
        artist = getIntent().getParcelableExtra(Constants.EXTRA_ARTIST);

        if (artist != null) {
            // устанавливаем заголовок в action bar
            getSupportActionBar().setTitle(artist.name);

            // загружаем и показываем обожку
            if (artist.cover != null) {
                if (!TextUtils.isEmpty(artist.cover.big))
                    Picasso.with(getApplicationContext()).load(artist.cover.big).into(image);
            }

            // создаем и показываем список жанров
            StringBuilder stringBuilder = new StringBuilder();

            if (artist.genres != null) {
                for (int i = 0; i < artist.genres.size(); i++) {
                    stringBuilder.append(artist.genres.get(i));

                    if (i < artist.genres.size() - 1)
                        stringBuilder.append(", ");
                }

                genres.setText(stringBuilder.toString());
            }

            // создаем и показываем строку со статистикой
            statistics.setText(Common.formatStatistics(getApplicationContext(), artist.tracks, artist.albums, Constants.STATISTICS_TYPE_INFO));

            // показываем биографию
            text.setText(artist.description);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // обработка нажатия кнопки назад на action bar
        if (id == android.R.id.home) {
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
