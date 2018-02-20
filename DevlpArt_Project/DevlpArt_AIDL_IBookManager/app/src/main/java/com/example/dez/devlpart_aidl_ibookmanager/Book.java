package com.example.dez.devlpart_aidl_ibookmanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dez on 2017/12/12.
 */

public class Book implements Parcelable {

    private int bookId;
    private String bookName;

    public Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>(){

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };



    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    @Override
    public String toString() {

        return "[bookId:"+bookId + "," + "bookName:"+ bookName+"]";
    }
}


