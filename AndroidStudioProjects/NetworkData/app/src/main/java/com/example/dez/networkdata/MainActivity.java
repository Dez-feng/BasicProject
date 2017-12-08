package com.example.dez.networkdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String readData(InputStream is, String charsetname) throws IOException
    {
        ByteArrayOutputStream  outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = is.read(buffer)) != -1 )
        {
            outputStream.write(buffer,0,len);
        }

        byte[] data = outputStream.toByteArray();

        outputStream.close();
        is.close();

        return new String(buffer,charsetname);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button1:
                new Thread()
                {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://www.sohu.com");

                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                            conn.setReadTimeout(5000);

                            conn.setRequestMethod("GET");

                            String result =  readData(conn.getInputStream(),"GBK");

                            Log.i("info",result);

                            conn.disconnect();

                        }
                        catch(MalformedURLException m)
                        {
                            m.printStackTrace();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }.start();

            break;
        }

    }

}
