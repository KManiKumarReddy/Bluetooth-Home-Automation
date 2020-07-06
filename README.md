# Bluetooth-Home-Automation

### Description
A solution to control home appliances using a Bluetooth device (an Android smartphone).

This repository consists of source code for an Android app as well as Arduino configuration.

### Requirements
Before you begin, ensure you have the following:

1. Arduino development board
2. HC-05 or HC-06 Bluetooth module
3. 5V DC / 220V AC relays
4. Android Bluetooth device
5. Connecting wires.

### Steps to set up Arduino
Complete the following steps to set up Arduino. You can use the [Arduino documentation](https://www.arduino.cc/en/Guide/HomePage) for reference:

* Upload the Arduino code in the repository root for the Arduino controller
* Connect PIN 11(TX) pin of Arduino to RX pin of HC-05
* Connect PIN 10(RX) pin of Arduino to TX pin of HC-05
* Connect 5V of Arduino to V<sub>in</sub> of HC-05 and V<sub>cc</sub> of relays
* Connect GND of Arduino to GND of HC-05 and GND of relays
* Connect IN of relay to PIN 13 of Arduino board(you are free to use any pin and also multiple pins, just update the arduino code)
* Connect 220V AC Line to Pole and Load(appliance) to NO of the relay
* Power the Arduino board and you're ready to use. Refer to circuit diagram for setup:

### Circuit Diagram
![Circuit diagram](https://raw.githubusercontent.com/KManiKumarReddy/Bluetooth-Home-Automation/master/circuit.png)

### How to use
* Build and install the app on an Android Bluetooth device.
* Turn Bluetooth ON and pair with HC-05 using the default passcode '1234' (Feel free to change this).
* Open the app, click on select controller and select the HC-05
* Use ON/OFF buttons to control the appliance.

### Purpose
Mini Project for Bachelor of Technology, CVR College of Engineering, Hyderabad


### Android Application
 - Features
    1) Send Signals via Bluetooth.
    2) List of Available Devices.
    3) Switch On/Off any devices.
  
 - Tools
    1) [Shared Preferences](https://developer.android.com/reference/android/content/SharedPreferences)
    2) [Bluetooth Socket](https://developer.android.com/reference/android/bluetooth/BluetoothSocket)
    3) [File](https://developer.android.com/reference/java/io/File)
    4) [Media Store](https://developer.android.com/reference/android/provider/MediaStore)
    5) [URI](https://developer.android.com/reference/android/net/Uri)

### License
This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.

Any copyright is dedicated to the Public Domain.
http://creativecommons.org/publicdomain/zero/1.0/

### Developed by
Mani Kumar Reddy K, Pramod Deshpande and Mallikarjun K
