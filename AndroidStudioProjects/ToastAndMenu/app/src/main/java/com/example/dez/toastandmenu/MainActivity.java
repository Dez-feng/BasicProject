package com.example.dez.toastandmenu;

import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)this.findViewById(R.id.contextMenu);
        registerForContextMenu(button);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item1 = menu.add(Menu.NONE,Menu.FIRST,0,"添加");
        item1.setIcon(android.R.drawable.ic_menu_add);
        MenuItem item2 = menu.add(Menu.NONE,Menu.FIRST+1,1,"删除");
        item2.setIcon(android.R.drawable.ic_menu_delete);
        MenuItem item3 = menu.add(Menu.NONE,Menu.FIRST+2,2,"退出");
        item3.setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        SubMenu subMenu = menu.addSubMenu(Menu.NONE,Menu.FIRST+3,3,"SubMenu");
        subMenu.add(Menu.NONE,Menu.FIRST+4,4,"修改");
        subMenu.add(Menu.NONE,Menu.FIRST+5,5,"查找");

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case Menu.FIRST:
                Toast.makeText(this,"添加",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 1:
                Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 2:
                finish();
                break;
            case Menu.FIRST + 4:
                break;
            case Menu.FIRST + 5:
                break;

            default:
                break;

        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        if(v == button){
            menu.setHeaderTitle("上下文菜单");
            menu.add(Menu.NONE,0,0,"上下文菜单1");
            menu.add(Menu.NONE,0,0,"上下文菜单2");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.button1:
                Toast toast = Toast.makeText(getApplicationContext(),"默认的Toast",
                        Toast.LENGTH_SHORT);
                toast.show();

                break;

            case R.id.button2:
                Toast toast2 = Toast.makeText(getApplicationContext(),"自定义显示位置的Toast",
                        Toast.LENGTH_SHORT);
                toast2.setGravity(Gravity.CENTER|Gravity.TOP,-50,100);

                toast2.show();
                break;

            case R.id.button3:
                Toast toast3 = Toast.makeText(getApplicationContext(),"显示带图片的Toast",
                        Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.CENTER,0,0);

                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.mipmap.ic_launcher);

                LinearLayout toastView = (LinearLayout)toast3.getView();

                toastView.addView(imageView,0);

                toast3.show();
                break;

            case R.id.button4:
                LayoutInflater inflater = getLayoutInflater();

                View layout = inflater.inflate(R.layout.custom2,(ViewGroup)findViewById(R.id.llToast));

                ImageView image = (ImageView)layout.findViewById(R.id.tvImageToast);
                image.setImageResource(R.mipmap.ic_launcher);

                TextView text = (TextView)layout.findViewById(R.id.tvTextToast);
                text.setText("完全自定义Toast");

                Toast toast4 = new Toast(getApplicationContext());
                toast4.setGravity(Gravity.CENTER,0,0);
                toast4.setDuration(Toast.LENGTH_SHORT);
                toast4.setView(layout);
                toast4.show();

                break;

            case R.id.button5:

                handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     showToast();
                                 }
                             }
                );
                break;

            case R.id.contextMenu:

                button.showContextMenu();
                break;

            default:
                break;
        }

    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch(what) {
                case 1:
                    showToast();

                default:
                    break;

            }


            super.handleMessage(msg);
        }
    };

    public void showToast()
    {
        Toast toast = Toast.makeText(getApplicationContext(),"Toast在其他线程中调用显示",Toast.LENGTH_SHORT);
        toast.show();
    }


}
