package com.modetransportation.batch.core;

import com.modetransportation.batch.core.JAXBUnmarshaller;
import com.modetransportation.batch.core.Processsor;
import com.modetransportation.batch.dao.APDAO;
import com.modetransportation.batch.model.ap.TpDoc;

public class APProcessor implements Processsor {

	public void process() {
		String filePath = "C:/Users/Subhash/workspace/ies-feed/src/main/resources/OF2017071408005239.AP";
		JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller();
		TpDoc ap = (TpDoc) unmarshaller.unmarshall(filePath, TpDoc.class);
		APDAO dao = new APDAO();
		dao.saveAP(ap);
		
	}

}
