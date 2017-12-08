package com.example.dez.contentprovider;


import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.ContentValues;
import android.content.ContentUris;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    private EditText etName,etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)this.findViewById(R.id.edittext1);
        etPhone = (EditText)this.findViewById(R.id.edittext2);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.button1:

                  String name = etName.getText().toString();
                  String phone = etPhone.getText().toString();

                  //输入一条假数据，来获取这个插入的id值
                  ContentValues values = new ContentValues();
                  Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI,values);

                  long id = ContentUris.parseId(rawContactUri);

                  values.put(Data.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
                  values.put(Data.RAW_CONTACT_ID,id);
                  values.put(StructuredName.GIVEN_NAME,name);

                  getContentResolver().insert(Data.CONTENT_URI,values);
                  values.clear();


                  values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
                  values.put(Data.RAW_CONTACT_ID,id);
                  values.put(Phone.NUMBER,phone);
                  values.put(Phone.TYPE,Phone.TYPE_MOBILE);
                  getContentResolver().insert(Data.CONTENT_URI,values);

                  Log.i("info","联系人信息已插入");
                  break;
                default:
                    break;


        }


    }
}


















