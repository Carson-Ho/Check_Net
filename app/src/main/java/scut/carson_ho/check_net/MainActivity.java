package scut.carson_ho.check_net;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NetWorkStateReceiver netWorkStateReceiver;
    private Button btn_wifi_on,btn_wifi_off;
    private WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取wifi管理服务
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        btn_wifi_on = (Button) findViewById(R.id.btn_wifi_on);
        btn_wifi_off = (Button) findViewById(R.id.btn_wifi_off);
        btn_wifi_on.setOnClickListener(this);
        btn_wifi_off.setOnClickListener(this);
    }

    //在onResume()方法注册
    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");
        super.onResume();
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wifi_off:{
                //获取wifi开关状态
                //wifi打开状态则关闭
                wifiManager.setWifiEnabled(false);
                Toast.makeText(MainActivity.this, "wifi已关闭", Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.btn_wifi_on: {
                //关闭状态则打开
                wifiManager.setWifiEnabled(true);
                Toast.makeText(MainActivity.this, "wifi已打开", Toast.LENGTH_SHORT).show();
            }
                break;
        }
    }
}
