package com.example.yauheni_skarakhodau.epamlearnwork;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;

public class ItemData implements Parcelable {

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>() {

        @Override
        public ItemData createFromParcel(final Parcel in) {
            return new ItemData(in);
        }

        @Override
        public ItemData[] newArray(final int size) {
            return new ItemData[size];
        }
    };
    private final String data;
    private final String description;
    private int visibility;

    ItemData(final String data, final int itsPos) {
        this.data = data;
        description = DataProviderClass.getNumericDescriptionFrom0ToI(itsPos);
        visibility = ConstraintLayout.INVISIBLE;
    }

    private ItemData(final Parcel in) {
        visibility = in.readInt();
        data = in.readString();
        description = in.readString();
    }

    int switchSelection() {
        switch (visibility) {
            case ConstraintLayout.VISIBLE:
                this.visibility = ConstraintLayout.INVISIBLE;
                return -1;
            case ConstraintLayout.INVISIBLE:
                this.visibility = ConstraintLayout.VISIBLE;
                return 1;
            default:

                return 0;
        }
    }

    public String getDescription() {
        return description;
    }

    public int getVisibility() {
        return visibility;
    }

    public boolean isSelected() {
        return visibility == ConstraintLayout.VISIBLE;
    }

    public String getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(visibility);
        dest.writeString(data);
        dest.writeString(description);
    }
}
