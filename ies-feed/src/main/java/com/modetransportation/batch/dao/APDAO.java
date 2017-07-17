package com.modetransportation.batch.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.modetransportation.batch.model.ap.APEvent;
import com.modetransportation.batch.model.ap.TpDoc;
import com.modetransportation.batch.util.HibernateUtil;

public class APDAO {
	private static String reversal = "-R";

	private static String hql = "update APEvent set Void = 'Y' where shipmentreference = :shipmentreference and "+
			"financialpartyexternalId = :financialpartyexternalId and chargetypecode = :chargetypecode and chargeamount = :chargeamount";

	public boolean saveAP(TpDoc ap){

		for(APEvent event : ap.getPAYLOAD().getACCOUNTINGEVENT()){
			boolean isReversal = false;

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			try{

				if(event.getSHIPMENTREFERENCE().endsWith(reversal)){
					event.setReversal("Y");
					isReversal = true;

				}
				
				//is this required 
				/*else{
					event.setReversal("N");
				}*/

				session.save(event);
				session.getTransaction().commit();
				session.close();
				if(isReversal){
					voidOriginalRecord(event);
				}


			}catch(Exception ex){
				System.out.println(ex);
				session.close();
			}
			
		}

		return true;

	}

	private void voidOriginalRecord(APEvent event) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		try{

			Query query = session.createQuery(hql);
			query.setParameter("shipmentreference", event.getSHIPMENTREFERENCE().substring(0, 10));
			query.setParameter("financialpartyexternalId", event.getFINANCIALPARTYEXTERNALID());
			query.setParameter("chargetypecode", event.getCHARGETYPECODE());
			query.setParameter("chargeamount", event.getCHARGEAMOUNT().abs());

			System.out.println(query.toString());
			int rowsAffected = query.executeUpdate();


			if (rowsAffected > 0) {
				System.out.println("Voided " + rowsAffected + " rows.");
			}
			session.getTransaction().commit();
			session.close();

		}
		catch(Exception ex){
			System.out.println(ex);
			session.close();

		}

	}

}
