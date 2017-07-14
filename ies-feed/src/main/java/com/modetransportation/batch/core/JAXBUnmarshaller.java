package com.modetransportation.batch.core;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.modetransportation.batch.model.Party;
import com.modetransportation.batch.model.Shipment;
import com.modetransportation.batch.util.HibernateUtil;

public class JAXBUnmarshaller {
	public void unmarshall(String xml) {

	 try {

		File file = new File(xml);
		JAXBContext jaxbContext = JAXBContext.newInstance(Shipment.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Shipment shipment = (Shipment) jaxbUnmarshaller.unmarshal(file);
		System.out.println(shipment);
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(shipment);
		for(Party party : shipment.getParties().getParty() ){
			party.setShipment(shipment);
		session.save(party);
		}
		session.getTransaction().commit();
		session.close();
		
		HibernateUtil.shutdown();

	  } catch (JAXBException e) {
		e.printStackTrace();
	  }

	}
}
