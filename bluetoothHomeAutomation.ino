#include <SoftwareSerial.h>

SoftwareSerial hc05(10, 11);
int signl = 0;
int currentState = 0;

void setup() {
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
  hc05.begin(9600);
}

void loop() {
  if (hc05.available()) {
    signl = hc05.read();
  }
  if (signl == 'a' && !currentState) {
    digitalWrite(13, HIGH);
    currentState = 1;
  }
  if (signl == 'b' && currentState) {
    digitalWrite(13, LOW);
    currentState = 0;
  }
  delay(100);
}
