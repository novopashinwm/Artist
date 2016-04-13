package ru.lexxx.artists.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Описывает сущность обложка.
 */
public class Cover implements Parcelable {
    public String small; // маленькое изображение
    public String big; // большое изображение

    public Cover() {}

    // далее идет реализация интерфейса Parcelable
    protected Cover(Parcel in) {
        small = in.readString();
        big = in.readString();
    }

    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(big);
    }
}
