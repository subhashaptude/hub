package com.modetransportation.batch;

import com.modetransportation.batch.core.APProcessor;
import com.modetransportation.batch.core.ARProcessor;
import com.modetransportation.batch.core.Processsor;
import com.modetransportation.batch.core.ShipmentProcessor;
import com.modetransportation.batch.util.HibernateUtil;


public class App 
{
	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		App a = new App();
		a.processShipment();
		a.processAP();
		a.processAR();


		System.out.println( "Done!" );
		HibernateUtil.shutdown();
	}

	public void processShipment(){
		Processsor shipProcessor = new ShipmentProcessor();
		shipProcessor.process();
	}

	public void processAP(){
		Processsor apProcessor = new APProcessor();
		apProcessor.process();
	}

	public void processAR(){
		Processsor arProcessor = new ARProcessor();
		arProcessor.process();
	}

}
