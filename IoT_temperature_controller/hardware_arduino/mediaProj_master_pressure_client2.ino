#include <ArduinoJson.h>
#include <SPI.h>
#include <WiFi.h>
#include <Wire.h>

//wifi
char ssid[] = "ollehEgg_482"; 
char pass[] = "info34782";  
int keyIndex = 0;         // your network key Index number (needed only for WEP)

int status = WL_IDLE_STATUS;
//arduino master as server
WiFiServer server(80);
//arduino master receive clients
IPAddress aws_server(54, 70, 73, 67);


//mater : press pad
int pressPinHead = A0; //head
int pressPinCenter = A1; //middle part left
int pressPinBottom = A2; //middle part middle
int pressData[3] = {0,0,0};

//slave1 : room heating and temp
//slave2 : floor heating pad and temp
int SLAVE[2] = {1, 2};
int roomTemp;
int floorTempHumi[2] = {};

boolean autoModeFloor = 1; //매트 -> 0 : 수동 / 1 : 자동
boolean autoModeRoom = 1; //난방 -> 0 : 수동 / 1 : 자동
int sendDataFloor[4] = {0,0,0,0}; //매트에 보낼 데이터 배열
int sendDataRoom[4] = {0,0,0,0}; //난방에 보낼 데이터 배열

// Ajax : 차후 Ajax 액션을 위해 선언해두는 변수 입니다.
boolean gflag=false;
String parm;

void setup() {
  Wire.begin(); 
  Serial.begin(9600);
  while(!Serial){
    ;
  }

  if (WiFi.status() == WL_NO_SHIELD) {
    Serial.println("WiFi shield not present");
    while (true);
  }

  String fv = WiFi.firmwareVersion();
  if (fv != "1.1.0") {
    Serial.println("Please upgrade the firmware");
  }

  while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);
    status = WiFi.begin(ssid, pass);
    delay(10000);
  }
  server.begin();
  printWifiStatus();

  
}

void printWifiStatus() {
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());
  
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}

void loop() {
  //매트 압력 값 pressData 배열에 저장
  for (int pin = 0; pin < 3; pin++) {
    int pressPin = analogRead(pin);
    int pressVal = map(pressPin, 0, 1024, 0, 255);
    pressData[pin] = pressVal;
    //압력 데이터를 보낼 배열의 0,1,2번째 index에 저장
    sendDataFloor[pin] = pressData[pin]; 
    sendDataRoom[pin] = pressData[pin]; 
  }
  //각 매트, 난방의 4번째 index에 모드 저장
  sendDataFloor[3] = autoModeFloor; 
  sendDataRoom[3] = autoModeRoom; 

  //매트 압력 상태에 따라서 난방과 매트 온도를 조절하기 위해서
  //slave에게 sendData 배열 보내기
  
  //난방 slave에게 데이터 전송
  Wire.beginTransmission(SLAVE[0]);
  for(int i=0; i< 4; i++){
    Wire.write(sendDataRoom[i]);
  } 
  Wire.write('\n');
  Wire.endTransmission(SLAVE[0]);
  delay(10);
  RoomHeatingCommunication(0);
  delay(10);

  //매트 온도 slave에게 데이터 전송
  Wire.beginTransmission(SLAVE[1]);
  for(int i=0; i< 4; i++){
    Wire.write(sendDataFloor[i]);
  }
  Wire.write('\n');
  Wire.endTransmission(SLAVE[1]);
  delay(10);
  FloorHeatingCommunication(1);
  delay(10);

WiFiClient client = server.available();
  if (client) {
    Serial.println("new client");
    // an http request ends with a blank line
    boolean currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        Serial.write(c);
        // if you've gotten to the end of the line (received a newline
        // character) and the line is blank, the http request has ended,
        // so you can send a reply
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
          client.println("Refresh: 5");  // refresh the page automatically every 5 sec
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");
          // output the value of each analog input pin
          for (int analogChannel = 0; analogChannel < 6; analogChannel++) {
            int sensorReading = analogRead(analogChannel);
            client.print("</br>");
            client.print("ROOM ==> ");
            client.print("Head :");
            client.print(sendDataRoom[0]);
            client.print('\t');
            client.print("Center :");
            client.print(sendDataRoom[1]);
            client.print('\t');
            client.print("Bottom :");
            client.print(sendDataRoom[2]);
            client.print('\t');
            client.print("Mode (0:manual / 1:auto) : ");
            client.print(sendDataRoom[3]);
            client.print("</br>");
            client.print("Room temperature : ");
            client.print(roomTemp);
            client.print("</br>");
            
            client.print("FLOOR ==> ");
            client.print("Head :");
            client.print(sendDataFloor[0]);
            client.print('\t');
            client.print("Center :");
            client.print(sendDataFloor[1]);
            client.print('\t');
            client.print("Bottom :");
            client.print(sendDataFloor[2]);
            client.print('\t');
            client.print("Mode (0:manual / 1:auto) : ");
            client.println(sendDataFloor[3]);
            client.print("</br>");
            client.print("Floor temperature : ");
            client.print(floorTempHumi[0]);
            client.print('\t');
            client.print("humidity : ");
            client.println(floorTempHumi[1]);
          }
          client.println("</html>");
          break;
        }
        if (c == '\n') {
          // you're starting a new line
          currentLineIsBlank = true;
        } else if (c != '\r') {
          // you've gotten a character on the current line
          currentLineIsBlank = false;
        }
      }
    }
    // give the web browser time to receive the data
    delay(1);

    // close the connection:
    client.stop();
    Serial.println("client disonnected");
  }
}

//난방 slave로부터 데이터 받기
void RoomHeatingCommunication(int i) {
  Wire.requestFrom(SLAVE[i], 1); 
  for (int j = 0 ; j < 1 ; j++) {  
    roomTemp = Wire.read(); 
  }
  delay(500);
}

//매트 온도 slave로부터 데이터 받기
void FloorHeatingCommunication(int i) {
  Wire.requestFrom(SLAVE[i], 2); 
  for (int j = 0 ; j < 2 ; j++) {  
    int floorInfo = Wire.read(); 
    floorTempHumi[j]=floorInfo;
  }
  
  
  delay(500);
}
