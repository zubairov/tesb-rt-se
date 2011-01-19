
package org.sopera.services.crmservice;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.4.0-SNAPSHOT
 * 2011-01-17T11:54:14.921+02:00
 * Generated source version: 2.4.0-SNAPSHOT
 * 
 */
 
public class CRMService_Localhost_Server{

    protected CRMService_Localhost_Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new CRMServiceImpl();
        String address = "http://localhost:8888/soap/CRMServiceProvider/";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception { 
        new CRMService_Localhost_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}