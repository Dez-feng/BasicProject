package com.example.dez.devlpart_contentprovider_book2;

public class User {

    public int userId;
    public String userName;
    public boolean isMale;

    public User()
    {
    }

    public User(int bookId, String bookName)
    {
        this.userId = bookId;
        this.userName = bookName;
    }

    @Override
    public String toString() {
        return new String("[userId:"+userId+",userName:" + userName + ",isMale:" + isMale + "]");
    }
}