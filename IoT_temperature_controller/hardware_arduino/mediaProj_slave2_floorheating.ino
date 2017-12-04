#include <LCD.h>
#include <LiquidCrystal_I2C.h>
#include <Wire.h>
#include <DHT11.h>
#define SLAVE 2  

int received[4] = {0,0,0,0};

//LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);

int pin=2;            //temperature and humidity sensor
DHT11 dht11(pin);
const int buttonPinOn = 5; //heating on button
const int buttonPinOff = 6; //heatin off button
const int heatingPadPin = 7; //heating pad

const int heatingPadTempMin = 10; //최저 온도
const int heatingPadTempMax = 70; //최고 온도

int tempTemp = 0; //임시 온도 변수
int tempSetted = 70; //사용자가 세팅한 온도

float tempCurrent, humiCurrent;
float floorTempHumi[]={0,0};

void setup() {
  //lcd.begin(16,2); // sixteen characters across - 2 lines
  //lcd.backlight();

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

void loop () {
  /*lcd.print("TEMP : ");
  lcd.print(temp);
  lcd.print(" C");
  lcd.setCursor(0,1);
  lcd.print("HUMI : ");
  lcd.print(humi);
  lcd.print(" %");
  lcd.clear();*/
  
  dht11.read(humiCurrent, tempCurrent);
  floorTempHumi[0] = tempCurrent;
  floorTempHumi[1] = humiCurrent;

  Serial.print("Humidity(%): ");
  Serial.print(floorTempHumi[0],2);
  Serial.print('\t');
  Serial.print("Temperature :");
  Serial.print(floorTempHumi[1],2);
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
      adjustForFloorHeating();
    }
    if(digitalRead(buttonPinOff) == HIGH){
      digitalWrite(heatingPadPin, LOW);
      Serial.println("(manual) button off clicked");
      tempTemp = heatingPadTempMin;
      adjustForFloorHeating();
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
    //adjustForFloorHeating();
  }  
  
  delay(3000);
}

//master(매트 압력)으로부터 현재 사용자 모드(자동/수도)와 여부 데이터를 받는다
void receiveFromMaster(int receivedData) {
  char temp[4];
  for (int i = 0 ; i < receivedData ; i++) {
    // 수신 버퍼 읽기
    temp[i] = Wire.read(); 
    received[i] = temp[i];
  }
}

//지속적으로 온도를 사용자가 세팅한 온도로 맞춰준다
void adjustForFloorHeating() { 
  if( tempCurrent <= tempTemp ){
    digitalWrite(heatingPadPin, HIGH);
  } else if( tempCurrent > tempTemp ){
    digitalWrite(heatingPadPin, LOW);
  }
  delay(1);
}

//현재 측정되는 매트 온도를 내보낸다.
void sendToMaster() {
  uint8_t Buffer[2];
  Buffer[0] = floorTempHumi[0];
  Buffer[1] = floorTempHumi[1];
  Wire.write(Buffer,2);
}
