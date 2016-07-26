
package com.kmanikumarreddy.bluetoothhomeautomation;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class SelectController extends AppCompatActivity {
    private BluetoothAdapter myBluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private ListView myListView;
    private ArrayAdapter<String> BTArrayAdapter;

//    final BroadcastReceiver bReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
//                BTArrayAdapter.notifyDataSetChanged();
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_controller);

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        myListView = (ListView)findViewById(R.id.deviceListView);
        BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myListView.setAdapter(BTArrayAdapter);

        pairedDevices = myBluetoothAdapter.getBondedDevices();


        for(BluetoothDevice device : pairedDevices)
            BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

//        if (myBluetoothAdapter.isDiscovering()) {
//            // the button is pressed when it discovers, so cancel the discovery
//            myBluetoothAdapter.cancelDiscovery();
//        }
//        else {
//            BTArrayAdapter.clear();
//            myBluetoothAdapter.startDiscovery();
//            registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
//        }

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] deviceDetails = ((TextView)view).getText().toString().split("\n");
//                BluetoothDevice device = myBluetoothAdapter.getRemoteDevice((deviceDetails[0]);
//                device.createBond();
                SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("controllerName", deviceDetails[0]);
                editor.putString("controllerAddress", deviceDetails[1]);
                editor.commit();
                finish();
            }
        });
    }
}
