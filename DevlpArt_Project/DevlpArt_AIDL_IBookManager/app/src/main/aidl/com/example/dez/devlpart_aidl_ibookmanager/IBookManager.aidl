// IBookManager.aidl
package com.example.dez.devlpart_aidl_ibookmanager;

// Declare any non-default types here with import statements

import com.example.dez.devlpart_aidl_ibookmanager.Book;
import com.example.dez.devlpart_aidl_ibookmanager.IOnNewBookArrivedListener;

interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
