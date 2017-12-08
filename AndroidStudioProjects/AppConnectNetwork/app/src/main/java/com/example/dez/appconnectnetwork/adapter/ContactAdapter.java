package com.example.dez.appconnectnetwork.adapter;

import java.io.File;
import java.util.List;

import android.os.AsyncTask;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dez.appconnectnetwork.R;
import com.example.dez.appconnectnetwork.domain.Contact;
import com.example.service.ContactService;

/**
 * Created by Dez on 2017/6/25.
 */

public class ContactAdapter extends BaseAdapter{

    private List<Contact> data;
    private int listviewItem;
    private File cache;
    LayoutInflater layoutInflater;

    public ContactAdapter(Context context, List<Contact> data, int listviewItem, File cache) {
        this.data = data;
        this.listviewItem = listviewItem;
        this.cache = cache;
        layoutInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        TextView textView = null;

        if(convertView == null){
            convertView = layoutInflater.inflate(listviewItem,null);
            imageView = (ImageView)convertView.findViewById(R.id.imageView);
            textView = (TextView)convertView.findViewById(R.id.textView);
            convertView.setTag(new DataWrapper(imageView, textView));
        }else{
            DataWrapper dataWrapper = (DataWrapper)convertView.getTag();
            textView = dataWrapper.textView;
            imageView = dataWrapper.imageView;
        }

        Contact contact = data.get(position);
        textView.setText(contact.getName());
        asyncImageLoad(imageView,contact.getImg());

        return convertView;
    }
    
    private void asyncImageLoad(ImageView imageView, String path){

        AsyncImageTask asyncImageTask = new AsyncImageTask(imageView);
        asyncImageTask.execute(path);

    }



    private final class AsyncImageTask extends AsyncTask<String, Integer, Uri>{

        private ImageView imageView;
        public AsyncImageTask(ImageView imageView) {
            this.imageView = imageView;

        }


        @Override
        protected Uri doInBackground(String... params) {
            try{
                return ContactService.getImage(params[0],cache);

            }catch(Exception e)
            {
                Log.e("info",Log.getStackTraceString(e));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Uri result) {
            if(imageView != null && result != null)
                imageView.setImageURI(result);
        }
    }


    private final class DataWrapper{
        public ImageView imageView;
        public TextView textView;

        public DataWrapper(ImageView imageView, TextView textView)
        {
            this.imageView = imageView;
            this.textView = textView;
        }

    }

}





