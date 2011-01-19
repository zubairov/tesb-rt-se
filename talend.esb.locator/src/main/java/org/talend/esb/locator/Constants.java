package org.talend.esb.locator;

import javax.xml.namespace.QName;

public class Constants {

	public static final String HOST = "localhost";
	public static final String PORT = "2181";
	public static final QName SERVICENAME = QName
			.valueOf("{http://cxf.sample.esb.talend.org/}GetTaskService");
	public static final QName PORTNAME = QName
			.valueOf("{http://cxf.sample.esb.talend.org/}GetTaskServiceImplPort");
	public static final String LOCATORENDPOINT = HOST + ":" + PORT;
}
