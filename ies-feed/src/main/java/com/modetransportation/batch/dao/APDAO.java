package com.modetransportation.batch.dao;

import org.hibernate.Session;

import com.modetransportation.batch.model.ap.ACCOUNTINGEVENT;
import com.modetransportation.batch.model.ap.TpDoc;
import com.modetransportation.batch.util.HibernateUtil;

public class APDAO {

	public boolean saveAP(TpDoc ap){

		try{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		for(ACCOUNTINGEVENT event : ap.getPAYLOAD().getACCOUNTINGEVENT()){
			session.save(event);
		}

		session.getTransaction().commit();
		session.close();
		//HibernateUtil.shutdown();

		return true;
	
		}catch(Exception ex){
			return false;
		}
	}



	
}
