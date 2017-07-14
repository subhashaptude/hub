package com.modetransportation.batch;

import com.modetransportation.batch.core.Processsor;


public class App 
{
	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		App a = new App();
		a.processShipment();
		System.out.println( "Done!" );
	}

	public void processShipment(){
		Processsor shipprocessor = new ShipmentProcessor();
		shipprocessor.process();
	}

	public void processAP(){

	}

	public void processAR(){

	}

}
