package com.modetransportation.batch.dao;

import org.hibernate.Session;

import com.modetransportation.batch.model.ar.ACCOUNTINGEVENT;
import com.modetransportation.batch.model.ar.TpDoc;
import com.modetransportation.batch.util.HibernateUtil;

public class ARDAO {

	public boolean saveAR(TpDoc ar){

		try{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		for(ACCOUNTINGEVENT event : ar.getPAYLOAD().getACCOUNTINGEVENT()){
			session.save(event);
		}

		session.getTransaction().commit();
		session.close();
		

		return true;
	
		}catch(Exception ex){
			return false;
		}
	}



	
}
