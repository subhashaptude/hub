package com.modetransportation.batch.core;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBUnmarshaller {
	public Object unmarshall(String filePath, Class<?> xmlType) {
		Object unmarshalledClass = null;

		try {

			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(xmlType);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			unmarshalledClass = (Object) jaxbUnmarshaller.unmarshal(file);
			System.out.println(unmarshalledClass);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return unmarshalledClass;
	
	}
}
