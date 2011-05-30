/*
 * #%L
 * Service Locator Client for CXF
 * %%
 * Copyright (C) 2011 Talend Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.talend.esb.servicelocator.cxf.internal;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import org.apache.cxf.clustering.FailoverStrategy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.easymock.Capture;
import org.junit.Test;
import org.talend.esb.servicelocator.client.ServiceLocator;

public class LocatorClientEnablerTest {
	
	Endpoint ENDPOINT = createMock(Endpoint.class);

	@Test
	public void enableClient() {
		ServiceLocator sl = createMock(ServiceLocator.class);
		
		Capture<LocatorTargetSelector> capturedSelector = new Capture<LocatorTargetSelector>();
		
		Client client = createMock(Client.class);
		expect(client.getEndpoint()).andStubReturn(ENDPOINT);
		client.setConduitSelector(capture(capturedSelector));
		replay(client);

		LocatorClientEnabler clientRegistrar = new LocatorClientEnabler();
		clientRegistrar.setServiceLocator(sl);
		// clientRegistrar.setLocatorSelectionStrategy(new DefaultSelectionStrategy());
		clientRegistrar.setLocatorSelectionStrategy(new EvenDistributionSelectionStrategy());
		clientRegistrar.enable(client);
		
		LocatorTargetSelector selector = capturedSelector.getValue();
		assertEquals(ENDPOINT, selector.getEndpoint());

		FailoverStrategy strategy = selector.getStrategy();
		assertThat(strategy, instanceOf(LocatorSelectionStrategy.class));
		
		

		LocatorSelectionStrategy locatorstrategy = (LocatorSelectionStrategy) strategy; 
		assertSame(sl, locatorstrategy.getServiceLocator());
		
		verify(client);
	}
	
}
