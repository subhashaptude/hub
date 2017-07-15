package com.modetransportation.batch.dao;

import org.hibernate.Session;

import com.modetransportation.batch.model.Container;
import com.modetransportation.batch.model.Location;
import com.modetransportation.batch.model.Party;
import com.modetransportation.batch.model.Reference;
import com.modetransportation.batch.model.Shipment;
import com.modetransportation.batch.model.Shipment.Charges.Charge;
import com.modetransportation.batch.util.HibernateUtil;

public class ShipmentDAO {

	public boolean saveShipment(Shipment shipment){

		try{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(shipment);


		for(Party party : shipment.getParties().getParty() ){
			party.setShipment(shipment);
			session.save(party);
		}
		
		for(Location location : shipment.getLocations().getLocation() ){
			location.setShipment(shipment);
			session.save(location);
		}
		
		for(Reference reference : shipment.getReferences().getReference() ){
			reference.setShipment(shipment);
			session.save(reference);
		}

		for(Container container : shipment.getContainers().getContainer() ){
			container.setShipment(shipment);
			session.save(container);
		}

		for(Container container : shipment.getContainers().getContainer() ){
			for(Container.Charges.Charge charge : container.getCharges().getCharge() ){
				charge.setContainer(container);
				session.save(charge);
			}
			
			for(Container.Contents.Content content : container.getContents().getContent() ){
				content.setContainer(container);
				session.save(content);
			}
		}
		
			for(Charge charge : shipment.getCharges().getCharge() ){
			charge.setShipment(shipment);
			session.save(charge);
		}


		/*for(Segment segment : shipment.getRouting().getSegment() ){
			segment.setShipment(shipment);
			session.save(segment);
		}
		 */

		session.getTransaction().commit();
		session.close();
		//HibernateUtil.shutdown();

		return true;
	
		}catch(Exception ex){
			return false;
		}
	}

	
}
