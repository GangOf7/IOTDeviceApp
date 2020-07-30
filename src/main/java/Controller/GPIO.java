package Controller;
/*
 *
 * **********************************************************************
 * Developer     :  A Nandy
 * PROJECT       :  Kalypso
 * FILENAME      :  GPIO.java
 * REF			:  pi4j
 * **********************************************************************
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

import singleton.Singletons;

import com.pi4j.system.SystemInfo;

public final class GPIO implements Singletons {
	private Logger _logger = null;
	private double _temperatureValueInCelc;
	private double _pHValue;
	private double _turbidityInmgL;
	private double _waterDoInMgL;
	private double _waterSalinityInmgL;
	private GpioController _gpio = null;
	private GpioPinDigitalOutput pin29 = null; // led
	private W1Master w1Master = null; 

	public GPIO(Logger logger) {
		this._logger = logger;
		w1Master = new W1Master();
		_temperatureValueInCelc = Float.MIN_VALUE; //init with limit
		_pHValue = Float.MIN_VALUE;
		_turbidityInmgL = Float.MIN_VALUE;
		_waterDoInMgL = Float.MIN_VALUE;
		_waterSalinityInmgL = Float.MIN_VALUE;
	}
	
	public void resetGPIOInternalParams() {
		_temperatureValueInCelc = Float.MIN_VALUE;
		_pHValue = Float.MIN_VALUE;
		_turbidityInmgL = Float.MIN_VALUE;
		_waterDoInMgL = Float.MIN_VALUE;
		_waterSalinityInmgL = Float.MIN_VALUE;
		resetGPIOs();
	}
	
	private void resetGPIOs() {
		try {
			_gpio.shutdown();
			_gpio.unprovisionPin(pin29);
			_gpio= null;
			pin29=null;
			w1Master=null;
			w1Master = new W1Master();
		} catch (Exception e) {}
	}
	
	public double getWaterTemperature(){
		_temperatureValueInCelc = Float.MIN_VALUE;
        for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
            //System.out.printf("%-20s %3.1f°C %3.1f°F\n", device.getName(), device.getTemperature(), device.getTemperature(TemperatureScale.CELSIUS));
            if(device.getName().contains(tmpSense_DS18B20)){
            	_temperatureValueInCelc = device.getTemperature(TemperatureScale.CELSIUS);
            	break;
            }
        }
		return _temperatureValueInCelc;
	}	
	public double getWaterpH(){
		_pHValue = 6.80f;  //ideal
		return _pHValue;
	}	
	public double getWaterTurbidity(){
		_turbidityInmgL = 900.1f;  //alerted
		return _turbidityInmgL;
	}	
	public double getWaterO2(){
		_waterDoInMgL = 7.12f;  //ideal
		return _waterDoInMgL;
	}
	public double getWaterAmmonia(){  //Ammonia
		_waterSalinityInmgL =  0.50f ;  //ideal
		return _waterSalinityInmgL;
	}


	public static void CPUTemperature() {
		try {
			System.out.println("CPU Temerature: "+SystemInfo.getCpuTemperature());
		} catch (IOException | NumberFormatException | UnsupportedOperationException | InterruptedException e) {} 		
	}
	
	public void indicateProcessNotOk() {
		try {
			_gpio = GpioFactory.getInstance();
			pin29 = _gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "indicateProcessNotOk", PinState.HIGH);
			pin29.setShutdownOptions(true, PinState.LOW);
			pin29.pulse(1000, true);
			Thread.sleep(200);
			pin29.pulse(800, true);
			
			_gpio.shutdown();
			_gpio.unprovisionPin(pin29);
		} catch (Exception e) {
			_logger.log(Level.WARNING, "Encountered problem in indicateProcessNotOk request: Stack: " + e.toString());
			resetGPIOs();
		}
	}
	
	public void indicateProcessOk() {
		try {
			_gpio = GpioFactory.getInstance();
			pin29 = _gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "indicateProcessOk", PinState.HIGH);
			pin29.setShutdownOptions(true, PinState.LOW);
			pin29.high();
			Thread.sleep(100);
			pin29.low();
			Thread.sleep(100);
			pin29.high();
			Thread.sleep(100);
			pin29.low();
			Thread.sleep(100);
			pin29.high();
			Thread.sleep(100);
			pin29.low();
			
			_gpio.shutdown();
			_gpio.unprovisionPin(pin29);
		} catch (Exception e) {
			_logger.log(Level.WARNING, "Encountered problem in indicateProcessOk request: Stack: " + e.toString());
			resetGPIOs();
		}
	}






}

