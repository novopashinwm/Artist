package ru.lexxx.artists.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Описывает сущность артист.
 */
public class Artist implements Parcelable {
    public int id; // идентификатор
    public String name; // имя
    public ArrayList<String> genres; // список жанров
    public int tracks; // кол-во треков
    public int albums; // кол-во альбомов
    public String link; // ссылка
    public String description; // описание
    public Cover cover; // обложка

    // далее идет реализация интерфейса Parcelable
    protected Artist(Parcel in) {
        id = in.readInt();
        name = in.readString();
        genres = in.createStringArrayList();
        tracks = in.readInt();
        albums = in.readInt();
        link = in.readString();
        description = in.readString();
        cover = in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeStringList(genres);
        dest.writeInt(tracks);
        dest.writeInt(albums);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeParcelable(cover, flags);
    }
}
