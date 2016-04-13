package ru.lexxx.artists.view.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.lexxx.artists.R;
import ru.lexxx.artists.model.Artist;
import ru.lexxx.artists.utils.Common;
import ru.lexxx.artists.utils.Constants;

/**
 * Адаптер для списка артистов.
 */
public class ArtistsAdapter extends BaseAdapter {
    // содержит ссылки на виджеты экрана
    class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView genres;
        public TextView statistics;
    }

    // контекст
    private Context context;
    //
    private LayoutInflater layoutInflater;
    // список с данными
    private List<Artist> items;

    /**
     * Конструктор.
     * @param context контекст.
     * @param items список артистов.
     */
    public ArtistsAdapter(Context context, List<Artist> items) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_artist, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = (ImageView)view.findViewById(R.id.image);
            viewHolder.name = (TextView)view.findViewById(R.id.name);
            viewHolder.genres = (TextView)view.findViewById(R.id.genres);
            viewHolder.statistics = (TextView)view.findViewById(R.id.statistics);

            view.setTag(viewHolder);
        }

        // получаем ViewHolder из текущего view
        ViewHolder holder = (ViewHolder)view.getTag();

        // текущий артист
        Artist current = items.get(position);

        // загружаем и показываем обложку
        if (current.cover != null) {
            if (!TextUtils.isEmpty(current.cover.small))
                Picasso.with(context).load(current.cover.small).into(holder.image);
        }

        // имя артиста
        holder.name.setText(current.name);

        // создаем список жанров и выводим их
        StringBuilder stringBuilder = new StringBuilder();

        if (current.genres != null) {
            for (int i = 0; i < current.genres.size(); i++) {
                stringBuilder.append(current.genres.get(i));

                if (i < current.genres.size() - 1)
                    stringBuilder.append(", ");
            }

            holder.genres.setText(stringBuilder.toString());
        }

        // создаем строку со статистикой и выводим ее
        holder.statistics.setText(Common.formatStatistics(context, current.tracks, current.albums, Constants.STATISTICS_TYPE_LIST_ITEM));

        return view;
    }
}
