package com.example.dez.devlpart_contentprovider_book2;

/**
 * Created by Dez on 2018/1/17.
 */

public class Book {
    public int bookId;
    public String bookName;

    public Book()
    {
    }

    public Book(int bookId, String bookName)
    {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return new String("[bookId:"+bookId+",bookName"+bookName+"]");
    }
}
