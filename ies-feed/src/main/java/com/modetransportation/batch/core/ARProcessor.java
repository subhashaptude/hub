package com.modetransportation.batch.core;

import com.modetransportation.batch.core.JAXBUnmarshaller;
import com.modetransportation.batch.core.Processsor;
import com.modetransportation.batch.dao.ARDAO;
import com.modetransportation.batch.model.ar.TpDoc;

public class ARProcessor implements Processsor {

	public void process() {
		String filePath = "C:/Users/Subhash/workspace/ies-feed/src/main/resources/OF2017070808001004.AR";
		JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller();
		TpDoc ar = (TpDoc) unmarshaller.unmarshall(filePath, TpDoc.class);
		ARDAO dao = new ARDAO();
		dao.saveAR(ar);
		
		
	}

}
