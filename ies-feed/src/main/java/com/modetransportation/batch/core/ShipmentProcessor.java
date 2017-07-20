package com.modetransportation.batch.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.modetransportation.batch.dao.ShipmentDAO;
import com.modetransportation.batch.model.InvalidData;
import com.modetransportation.batch.model.Shipment;
import com.modetransportation.batch.util.ValidationUtil;

public class ShipmentProcessor implements Processsor {

	public void process() {
		try {
			File dir = new File("src/main/resources/data");
			String[] extensions = new String[] { "xml" };

			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, false);
			for (File file : files) {

				//String filePath = "C:/Users/Subhash/workspace/ies-feed/src/main/resources/Shipment.xml";
				JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller();
				Shipment shipment = (Shipment) unmarshaller.unmarshall(file.getAbsolutePath(), Shipment.class);
				List<InvalidData> invalidDataList = validate(file.getAbsolutePath(), shipment);

				if(invalidDataList.size() > 0){
					shipment.setValidationFailed("Y");
					saveInvalidData(invalidDataList);
				}
				saveShipment(shipment);
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveInvalidData(List<InvalidData> invalidDataList) {
		ValidationUtil v = new ValidationUtil();
		v.save(invalidDataList);
	}

	private List<InvalidData> validate(String filePath, Shipment shipment) {

		List<InvalidData> invalidDataList = new ArrayList<InvalidData>();
		InvalidData invalidData = null;

		ValidationUtil v = new ValidationUtil();
		Set<Integer> errorList = v.validate(filePath,"Shipment");


		for(int error : errorList){
			invalidData = new InvalidData();
			invalidData.setFailedRecord(shipment.getFileNumber());
			invalidData.setReasonCode(error);
			invalidDataList.add(invalidData);
		}
		return invalidDataList;
	}

	private void saveShipment(Shipment shipment) {
		ShipmentDAO dao = new ShipmentDAO();
		dao.persistShipment(shipment);
	}

}
