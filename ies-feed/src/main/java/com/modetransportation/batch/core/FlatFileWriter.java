package com.modetransportation.batch.core;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import com.modetransportation.batch.dao.FlatFileDAO;
import com.modetransportation.batch.model.Shipment;
import com.modetransportation.batch.model.flatfile.PrimaryOrderInfo;

public class FlatFileWriter {

	public void writeReversals() throws Exception {
		// create a StreamFactory
		StreamFactory factory = StreamFactory.newInstance();
		// load the mapping file
		factory.load("C://Users/Subhash/workspace/ies-feed/src/main/resources/mapping.xml");

		FlatFileDAO dao = new FlatFileDAO();
		Set<String> referenceList =  dao.getReversals(); 
		List<Shipment> shipmentList = dao.getShipment(referenceList);
		PrimaryOrderInfo primaryOrderInfo = new PrimaryOrderInfo();
		for(Shipment shipment: shipmentList)
		{
			primaryOrderInfo.setRecordType(01);
			primaryOrderInfo.setInitialFeedFlag("N");
			primaryOrderInfo.setDelta7Number(shipment.getFileNumber());
			primaryOrderInfo.setOfficeCode(shipment.getDivisionAlpha());
		

		// use a StreamFactory to create a BeanWriter
		BeanWriter out = factory.createWriter("reversalFile", new File("IES"+shipment.getFileNumber()+".FTP"));
		// write an Employee object directly to the BeanWriter
		out.write(primaryOrderInfo);
		out.flush();
		out.close();
		}
	}
}
