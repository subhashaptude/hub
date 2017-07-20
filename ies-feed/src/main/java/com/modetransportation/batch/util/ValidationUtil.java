package com.modetransportation.batch.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import com.modetransportation.batch.model.InvalidData;
import com.modetransportation.batch.model.Validation;
import com.modetransportation.batch.model.ValidationType;

public class ValidationUtil {

	public List<Validation> getValidationsList(String groupName){

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Validation where groupName = :groupName and active = 'Y'";
		Query query = session.createQuery(hql);
		query.setParameter("groupName", groupName);

		List<Validation> validationsList =  ((org.hibernate.query.Query) query).list();

		session.getTransaction().commit();
		session.close();


		return validationsList;

	}


	public Set<Integer> validate(String filePath, String groupName){
		FileInputStream file;
		Set<Integer> reasonCodes = new HashSet<>();

		try {

			file = new FileInputStream(new File(filePath));

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList;

			List<Validation> validationsList = getValidationsList(groupName);

			for (Validation validation : validationsList) {

				String expression = validation.getExpression();
				System.out.println(expression);
				nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

				if(ValidationType.NOTNULL.equals(validation.getValidationType())){

					for (int i = 0; i < nodeList.getLength(); i++) {
						if(nodeList.item(i).getFirstChild() == null){
							reasonCodes.add(validation.getId());
						}
						/*else{
							System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
						}*/

					}

				}

			}

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return reasonCodes;

	}


	public boolean save(List<InvalidData> invalidDataList){

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		try{

			for(InvalidData invalidData : invalidDataList) {
				session.save(invalidData);
			}
			session.getTransaction().commit();
			session.close();

			return true;

		}catch(Exception ex){
			System.out.println(ex);
			session.close();
			return false;
		}
	}


}
