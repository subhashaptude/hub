package com.work.batch;

import org.beanio.*;
import java.io.*;
import java.util.*;

public class BeanWriterExample {
    public static void main(String[] args) throws Exception {
        // create a StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.load("C://Users/Subhash/workspace/ies-feed/src/test/java/com/work/batch/mapping.xml");
        
        Employee employee = new Employee();
        employee.setFirstName("Jennifer");
        employee.setLastName("Jones");
        employee.setTitle("Marketing");
        employee.setSalary(60000);
        employee.setHireDate(new Date());
        
        Staff staff = new Staff();
        staff.setFirstNameOther("JenniferLopez");
        staff.setLastName("Jonesasdf");
        staff.setTitle("Marketingadsf");
        staff.setSalary(600);
        staff.setHireDate(new Date());
        
        // use a StreamFactory to create a BeanWriter
        BeanWriter out = factory.createWriter("employeeFile", new File("employee.FTP"));
        // write an Employee object directly to the BeanWriter
        out.write(employee);
        out.write(staff);
        out.flush();
        out.close();
    }
}
