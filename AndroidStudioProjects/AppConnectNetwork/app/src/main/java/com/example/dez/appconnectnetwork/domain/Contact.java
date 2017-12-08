package com.example.dez.appconnectnetwork.domain;

/**
 * Created by Dez on 2017/6/22.
 */

public class Contact {
    private int id;
    private String name;
    private String img;

    public Contact(){

        super();
    }

    public Contact(int id, String name, String img){
        super();
        this.id = id;
        this.name = name;
        this.img = img;

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName(){

        return this.name;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getImg(){

        return this.img;
    }

    public void setImg(String img)
    {

        this.img = img;
    }



}



