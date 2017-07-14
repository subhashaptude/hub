package com.modetransportation.batch;

import com.modetransportation.batch.core.JAXBUnmarshaller;
import com.modetransportation.batch.core.Processsor;

public class ShipmentProcessor implements Processsor {

	public void process() {
		String filePath = "C:/Users/Subhash/workspace/ies-feed/src/main/resources/Shipment.xml";
		JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller();
		unmarshaller.unmarshall(filePath);
		
	}

}
