package com.kmanikumarreddy.bluetoothhomeautomation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BluetoothHomeAutomation";

    private BluetoothSocket socket;
    private BluetoothDevice device;
    private OutputStream os;
    private BluetoothAdapter mBluetoothAdapter;
    public String deviceName;
    public String deviceAddress;
    UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = (TextView)findViewById(R.id.controllerName);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),R.string.Bluetooth_NA, Toast.LENGTH_LONG).show();
        }
        else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
        deviceName = preferences.getString("controllerName", "NA");
        deviceAddress = preferences.getString("controllerAddress", "");
        nameView.setText(deviceName);
        if(!deviceAddress.equals("")) {
            device = mBluetoothAdapter.getRemoteDevice(deviceAddress);
            try {
                socket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            mBluetoothAdapter.cancelDiscovery();
            try {
                socket.connect();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "socket connect failed: " + e.getMessage() + "\n");
                try {
                    socket.close();
                } catch (IOException e1) {
                    Log.e(TAG, "socket closing failed: " + e1.getMessage() + "\n");
                    Toast.makeText(getApplicationContext(), e1.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            try {
                os = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "getting output stream failed: " + e.getMessage() + "\n");
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Connect to a device",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (os != null && socket.isConnected()) {
            try {
                os.flush();
            } catch (IOException e) {
                Log.e(TAG, "flushing output stream failed: " + e.getMessage() + "\n");
                Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        try {
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            Log.e(TAG, "closing socket failed: " + e.getMessage() + "\n");
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void sendSignal(View view) {
        if(socket.isConnected()) {
            try {
                os.write(view.getResources().getResourceName(view.getId()).getBytes());
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getBaseContext(), "Connect to the seleceted bluetooth device first", Toast.LENGTH_LONG).show();
        }
    }

    public void selectFromList(View view) {
        Intent intent = new Intent(this,SelectController.class);
        startActivity(intent);
    }
}
