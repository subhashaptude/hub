package com.modetransportation.batch.core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.modetransportation.batch.core.JAXBUnmarshaller;
import com.modetransportation.batch.core.Processsor;
import com.modetransportation.batch.dao.APDAO;
import com.modetransportation.batch.model.ap.TpDoc;

public class APProcessor implements Processsor {

	public void process() {
		try {
			File dir = new File("src/main/resources/data");
			String[] extensions = new String[] { "AP" };

			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, false);
			for (File file : files) {
				System.out.println("file: " + file.getCanonicalPath());
				JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller();
				TpDoc ap = (TpDoc) unmarshaller.unmarshall(file.getAbsolutePath(), TpDoc.class);
				APDAO dao = new APDAO();
				dao.saveAP(ap);
			} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}


	}

}
