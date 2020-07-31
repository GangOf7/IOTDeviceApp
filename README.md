# IoTDeviceApp
<img src="https://github.com/GangOf7/IOTDeviceApp/blob/master/Banner.png"/> 

***Kalypso is the core IoT device software running on Raspberry PI. It collects ambient parameters from different digital sensors and can be deployed at remote locations***

# <img src="https://img.icons8.com/dotty/80/000000/relay-home-automation.png"/> Technology:-
- Java 11
- IoT Interface
- Maven
- Pi4J ARM 32

# <img src="https://img.icons8.com/cotton/64/000000/laptop-coding.png"/> Design IDE:-
Eclipse 2020, Remote debugger, PuTTY SSH

# <img src="https://img.icons8.com/nolan/96/futures.png"/> OverView:-
Kalypso is designed for running device hardware 24x7 with moderate fault tolerance, collecting sensor data and managing communication resources with different micro services from remote locations.
All of these have been done by keeping in mind such that we can upgrade the device software as frequently as possible based on client feedback. 
Kalypso monitors real life data 24x7 even if when there is temporary communication interrupt with its host and comes back as online as soon as service resumes.
It is reconfigurable at any time by its authorized host based on real life situations / seasonal requirement.

# <img src="https://img.icons8.com/cotton/64/000000/installing-updates.png"/> Installion Steps:-

 **1. Raspbian Install Prerequisites**
 - Before installing anything, let us first update the Raspbian OS running on PI. Connect the USB internet card in any USB port. Plug Raspberry pi into local LAN and search assigned IP address using any ip scanner tool like Advance IP scanner. Follow below steps -
```
    sudo raspi-config
    sudo apt-get update
    sudo apt-get upgrade
 ```
  
  **2. Installion and Build Steps**
 
 - Clone this porject  ```git clone <repo> <directory>```
 - We start first with installing Eclipse and remote debugger in the local system to run and debug this java app (if required).
 - To Build this project we have to install Maven from here: https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows for wndows system.
 - Download raspbian from here: https://www.raspberrypi.org/downloads/ and write it as boot drive in a micro SD card using any boot partition tool. 
 - Please follow the steps in Raspbian Install Prerequisites as mentioned above.
 - We also need to update Interfacing Options using  ```raspi-config```  as given above for communicating with digital sensors and also with the remote debugger.
 - Build this project in the local system using  ```mvn compile```  and place the final jar file (Kalypso.jar) into the Raspberyy pi local path. Or it can be downloaded from here: https://github.com/GangOf7/IOTDeviceApp/blob/master/Kalypso.jar
 
 We can now connect the Raspberry PI using SSH (local connection), navigate to the desired directory and run the jar using  ``` java -jar Kalypso.jar ```  
 
 **3. Hardware**
 Raspberry PI 3b+, DS18B20, USB Internet Card
 

# <img src="https://img.icons8.com/nolan/96/system-task.png"/>  System Requirements:-
 
  1) Memory: 512 MB.
  
  2) HDD : 500 MB.
  
  3) Internet Connectivity


