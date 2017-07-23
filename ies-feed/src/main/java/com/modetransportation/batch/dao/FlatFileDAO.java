package com.modetransportation.batch.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import org.hibernate.Session;

import com.modetransportation.batch.model.Shipment;
import com.modetransportation.batch.model.ap.APEvent;
import com.modetransportation.batch.util.HibernateUtil;

public class FlatFileDAO {

	private static String reversals = "from APEvent where reversal = 'Y'";
	//private static String shipments = "from Shipment where fileNumber = :fileNumber";

	public Set<String> getReversals( ){
		Set<String> referenceList = new HashSet<>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery(reversals);
		try{

			List<APEvent> reversals = ((org.hibernate.query.Query) query).list();

			for (APEvent reversal : reversals) {
				referenceList.add(reversal.getSHIPMENTREFERENCE().substring(0, 10));
			}

		}catch(Exception ex){
			System.out.println(ex);
			session.close();
		}
		session.close();
		return referenceList;

	}

	public List<Shipment> getShipment(Set<String> referenceList) {
		
		List<Shipment> shipmentsList = new ArrayList<Shipment>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Shipment shipment = null;
		
		for(String reference : referenceList){
			shipment =  (Shipment) session.get(Shipment.class, reference);
			shipmentsList.add(shipment);
		}
		session.close();

		return shipmentsList;
	}
}
