package com.example.service;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;

import android.util.Log;
import android.util.Xml;
import android.net.Uri;
import com.example.dez.appconnectnetwork.domain.Contact;
import com.example.dez.appconnectwork.utils.MD5;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Dez on 2017/6/22.
 */

public class ContactService {


    public static List<Contact> getContacts() throws Exception{

        String path = "http://192.168.100.16:8080/web/list.xml";
        HttpURLConnection conn = (HttpURLConnection)new URL(path).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
//        Log.i("info",Integer.toString(conn.getResponseCode()));
        if(conn.getResponseCode() == 200){
            return parseXML(conn.getInputStream());
        }

       return null;
    }

    public static List<Contact> parseXML(InputStream xml) throws Exception{
        List<Contact>  contacts = new ArrayList<Contact>();
        Contact contact = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml,"UTF-8");
        int event = pullParser.getEventType();
        while(event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_TAG:
                    if ("Contact".equals(pullParser.getName())) {
                        contact = new Contact();
                        contact.setId(new Integer(pullParser.getAttributeValue(0)));
                    } else if ("name".equals(pullParser.getName())) {
                        contact.setName(pullParser.nextText());
                    } else if ("image".equals(pullParser.getName())) {
                        contact.setImg(pullParser.getAttributeValue(0));
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("Contact".equals(pullParser.getName())) {
                        contacts.add(contact);
                        contact = null;
                    }
                    break;
            }
            event = pullParser.next();
        }

        return contacts;
    }

    public static Uri getImage(String path, File cacheDir) throws Exception{

        File localFile = new File(cacheDir, MD5.getMD5(path)+path.substring(path.lastIndexOf(".")));
        if(localFile.exists())
        {
            return Uri.fromFile(localFile);

        }else{
            HttpURLConnection conn = (HttpURLConnection)new URL(path).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200){
                FileOutputStream outStream = new FileOutputStream(localFile);
                InputStream inputStream = conn.getInputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len = inputStream.read(bytes)) != -1){
                    outStream.write(bytes,0,len);

                }
                inputStream.close();
                outStream.close();

                return Uri.fromFile(localFile);
            }

        }

        return null;

    }

}





