package org.talend.esb.locator.samples;

import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.talend.esb.locator.Constants;
import org.talend.esb.locator.EndpointResolver;
import org.talend.esb.sample.cxf.IGetTaskService;

public class CXFConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create endpoint resolver
		EndpointResolver er = new EndpointResolver(Constants.SERVICENAME,
				Constants.LOCATORENDPOINT);
		String endpoint = er.selectEndpoint();
		if (endpoint==null) {
			System.out.println("Endpoint not found for service " + Constants.SERVICENAME.toString());
		} else {
		System.out.println(er.selectEndpoint());
		// Create service
		Service service = Service.create(Constants.SERVICENAME);
		// Add endpoint from endpoint resolve
		service.addPort(Constants.PORTNAME, SOAPBinding.SOAP11HTTP_BINDING,
				er.selectEndpoint());
		
		IGetTaskService client = service.getPort(Constants.PORTNAME,
				IGetTaskService.class);
		// Using of service
		System.out.println("_____________________________");
		System.out.println(client.echo("ping"));
		System.out.println("_____________________________");
		}

	}

}
