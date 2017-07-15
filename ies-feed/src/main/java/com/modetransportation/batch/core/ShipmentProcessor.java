package com.modetransportation.batch.core;

import com.modetransportation.batch.core.JAXBUnmarshaller;
import com.modetransportation.batch.core.Processsor;
import com.modetransportation.batch.dao.ShipmentDAO;
import com.modetransportation.batch.model.Shipment;

public class ShipmentProcessor implements Processsor {

	public void process() {
		String filePath = "C:/Users/Subhash/workspace/ies-feed/src/main/resources/Shipment.xml";
		JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller();
		Shipment shipment = (Shipment) unmarshaller.unmarshall(filePath, Shipment.class);
		ShipmentDAO dao = new ShipmentDAO();
		dao.saveShipment(shipment);
		
		
	}

}
