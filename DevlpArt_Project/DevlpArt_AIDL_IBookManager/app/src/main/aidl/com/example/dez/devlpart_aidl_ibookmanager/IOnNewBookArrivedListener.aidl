// IOnNewBookArrivedListener.aidl
package com.example.dez.devlpart_aidl_ibookmanager;
import com.example.dez.devlpart_aidl_ibookmanager.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {

    void OnNewBookArrived(in Book newBook);

}
