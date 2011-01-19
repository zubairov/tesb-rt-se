package org.talend.esb.locator;

import java.io.IOException;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.zookeeper.KeeperException;

public class EndpointResolver {

	private List<String> endpointsList;
	private LocatorClient lc;
	private QName serviceName;
	

	public EndpointResolver(QName serviceName, String locatorEndpoints) {
		this.serviceName = serviceName;
		try {
			lc = new LocatorClient();
			lc.setLocatorEndpoints(locatorEndpoints);
			lc.connect();
			endpointsList = receiveEndpointsList();
		} catch (IOException e) {
			System.err
					.println("Can not connect to zookeeper due to IOException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err
			.println("Can not connect to zookeeper due to InterruptedException");
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			System.err
			.println("Can not connect to zookeeper due to ServiceLocatorException");
			e.printStackTrace();
		}
		// TODO Set LOCATOR_ROOT for LocatorClient
	}

	private List<String> receiveEndpointsList() {
		List<String> endpointsList = null;

		try {
			endpointsList = lc.lookup(this.serviceName);
		} catch (KeeperException e) {
			System.err
					.println("Can not receive list of endpoints due to Keeper exception");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err
					.println("Can not receive list of endpoints due to Interrupted exception");
			e.printStackTrace();
		}

		return endpointsList;
	}

	public String selectEndpoint() {
		if (endpointsList.isEmpty()) {
			System.err.println("List of endpoints is empty");
		} else {
			return endpointsList.get(0);
		}

		return null;
	}

	public List<String> getEndpointsList() {
		return endpointsList;
	}

	public void refreshEndpointsList() {
		try {
			List<String> el = receiveEndpointsList();
			if (el == null) {
				System.err.println("Can not receive list of endpoint");
			}
		} catch (Exception e) {
			System.err
					.println("Can not refresh list of endpoints due to unknown exception");
			e.printStackTrace();
		}
	}
//	public List<String> lookupServices() throws KeeperException, InterruptedException {
//		  String providerPath = lc.LOCATOR_ROOT;
//		  Stat s = zk.exists(providerPath, false);
//		  if (s != null) {
//		   return decode(zk.getChildren(providerPath, false));
//		  } else {
//		   System.out.println("Lookup services failed, provider not known.");
//		   return Collections.emptyList();
//		  }
//		 }
}
