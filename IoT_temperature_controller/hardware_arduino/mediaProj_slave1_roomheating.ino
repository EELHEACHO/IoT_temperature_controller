#include <LCD.h>
#include <LiquidCrystal_I2C.h>
#include <Wire.h>
#define SLAVE 1

int received[4] = {0,0,0,0};

//LiquidCrystal_I2C lcd(0x3F,2,1,0,4,5,6,7,3,POSITIVE);

const int tempSensor = A0; // lm35 
const int buttonPinOn = 2; //heating on button
const int buttonPinOff = 8; //heatin off button
const int heatingPadPin = 7; //heating pad

const int heatingPadTempMin = 10; //최저 온도
const int heatingPadTempMax = 70; //최고 온도

int tempTemp = 0; //임시 온도 변수
int tempSetted = 70; //사용자가 세팅한 온도

float tempCurrent; //측정하는 온도
float roomTemp[]={0};

void setup() {
  //lcd.begin(16,2); // lcd setting
  //lcd.clear(); 

  // Wire 라이브러리 초기화(슬레이브)
  Wire.begin(SLAVE); 
  // 마스터로부터 데이터를 받을 때
  Wire.onReceive(receiveFromMaster);
  // 마스터의 데이터 전송 요구가 있을 때 처리할 함수 등록
  Wire.onRequest(sendToMaster);

  pinMode(buttonPinOn, INPUT_PULLUP);
  pinMode(buttonPinOff, INPUT_PULLUP);
  pinMode(heatingPadPin, OUTPUT);
  
  Serial.begin(9600);
}

void loop() {
  /*lcd.print("TEMPERATURE");
  lcd.setCursor(0,1);
  lcd.print("CURRENT : ");
  lcd.print(tempCurrent);
  lcd.print(" C");
  lcd.clear();*/

  int tempRead = analogRead(tempSensor);
  tempCurrent = ((5.0 * tempRead * 100.0)/1024.0);

  Serial.print("Current : ");
  Serial.print(tempCurrent);
  Serial.print("\t");
  Serial.print("Setted  :");
  Serial.print(tempSetted);
  Serial.print("\t");
  Serial.print("Temp  :");
  Serial.println(tempTemp);

  Serial.print("Head :");
  Serial.print(received[0]);
  Serial.print("\t");
  Serial.print("Center :");
  Serial.print(received[1]);
  Serial.print("\t");
  Serial.print("Bottom :");
  Serial.print(received[2]);
  Serial.print("\t");
  Serial.print("Mode (0:manual / 1:auto) : ");
  Serial.print(received[3]);
  Serial.println("\n");
  
  if(received[3]==0){ //수동 모드 일때
    if(digitalRead(buttonPinOn) == HIGH){
      digitalWrite(heatingPadPin, HIGH);
      Serial.println("(manual) button on clicked");
      tempTemp = tempSetted;
      adjustForRoomHeating();
    }
    if(digitalRead(buttonPinOff) == HIGH){
      digitalWrite(heatingPadPin, LOW);
      Serial.println("(manual) button off clicked");
      tempTemp = heatingPadTempMin;
      adjustForRoomHeating();
    }
  } else if(received[3]==1){ //자동 모드 일때
    if (received[0]>20 || received[1]>20 || received[2]>20) {
      digitalWrite(heatingPadPin, HIGH);
      Serial.println("(auto) start to heat up");
      tempTemp = heatingPadTempMax;
    } else if (received[0]<=20 && received[1]<=20 && received[2]<=20){
      digitalWrite(heatingPadPin, LOW);
      Serial.println("(auto) start to cool down");
      tempTemp = heatingPadTempMin;
    }
    //adjustForRoomHeating();
  }  

  roomTemp[0] = tempCurrent;
  delay(3000);
}

//master(매트 압력)으로부터 현재 사용자 모드(자동/수도)와 여부 데이터를 받는다
void receiveFromMaster(int receivedData) {
  int temp[4];
  for (int i = 0 ; i < receivedData ; i++) {
    // 수신 버퍼 읽기
    temp[i] = Wire.read(); 
    received[i] = temp[i];
  }
}

//지속적으로 온도를 사용자가 세팅한 온도로 맞춰준다
void adjustForRoomHeating() {   
  if( tempCurrent <= tempTemp ){
    digitalWrite(heatingPadPin, HIGH);
  } else if( tempCurrent > tempTemp ){
    digitalWrite(heatingPadPin, LOW);
  }
  delay(1);
}

//현재 측정되는 난방 온도를 내보낸다.
void sendToMaster() {
  uint8_t Buffer[1];
  Buffer[0] = roomTemp[0];
  Wire.write(Buffer, 1);
}
