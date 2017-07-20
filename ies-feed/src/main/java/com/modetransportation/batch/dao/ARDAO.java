package com.modetransportation.batch.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.modetransportation.batch.model.ar.AREvent;
import com.modetransportation.batch.model.ar.TpDoc;
import com.modetransportation.batch.util.HibernateUtil;

public class ARDAO {

	private static String reversal = "-R";
	private static String hql = "update AREvent set Void = 'Y' where ReferenceNumber = :ReferenceNumber and "+
			"FinancialPartyExternalId = :FinancialPartyExternalId and ChargeTypeCode = :ChargeTypeCode and ChargeAmount = :ChargeAmount";


	public boolean saveAR(TpDoc ar){

		for(AREvent event : ar.getPAYLOAD().getACCOUNTINGEVENT()){

			boolean isReversal = false;

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			try{

				if(event.getREFERENCENUMBER().endsWith(reversal)){
					event.setReversal("Y");
					isReversal = true;

				}

				// is this required
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


	private void voidOriginalRecord(AREvent event) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try{

			Query query = session.createQuery(hql);
			query.setParameter("ReferenceNumber", event.getREFERENCENUMBER().substring(0, 10));
			query.setParameter("FinancialPartyExternalId", event.getFINANCIALPARTYEXTERNALID());
			query.setParameter("ChargeTypeCode", event.getCHARGETYPECODE());
			query.setParameter("ChargeAmount", event.getCHARGEAMOUNT().abs());

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
