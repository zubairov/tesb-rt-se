package org.talend.esb.sample.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.ServerLifeCycleManager;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.service.model.EndpointInfo;

@WebService
public interface IGetTaskService {
	@WebMethod
	public String echo(String message);

	@WebMethod
	public boolean registerService(
			@WebParam(name = "serviceName") QName serviceName,
			@WebParam(name = "endpointAddress") String endpointAddress);
}
