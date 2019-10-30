#include <SoftwareSerial.h>
// Attach pin 13 to the led
// Insert the HC-05 into the Rx and Tx terminal in the arduino

SoftwareSerial hc05(10, 11);
int signl = 0;
int currentState = 0;

void setup() {
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);                        // by default setting led off.
  hc05.begin(9600);
}

void loop() {
  if (hc05.available()) {
    signl = hc05.read();
  }
  if (signl == 'a' && !currentState) {         // checking if the HC-05 have sent a or not if yes then led will glow
    digitalWrite(13, HIGH);                                                         
    currentState = 1;
  }
  if (signl == 'b' && currentState) {
    digitalWrite(13, LOW);
    currentState = 0;
  }
  delay(100);
}
