/*
 * #%L
 * Service Activity Monitoring :: Agent
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
package org.talend.esb.sam.agent.flowidprocessor;

import junit.framework.Assert;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.ExchangeImpl;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.junit.Test;
import org.talend.esb.sam.agent.flowidprocessor.FlowIdProducerIn;
import org.talend.esb.sam.agent.message.FlowIdHelper;

public class FlowIdProducerTest {

	
	@Test
	public void flowIdProducerInTest() {
		FlowIdProducerIn<Message> flowIdProducerIn = new FlowIdProducerIn<Message>();
		Message message = new MessageImpl();
		Exchange exchange = new ExchangeImpl();
		message.setExchange(exchange);
		String flowId = FlowIdHelper.getFlowId(message);
		
		Assert.assertNull("FlowId should be null before FlowIdProducerIn handleMessage()", flowId);
		flowIdProducerIn.handleMessage(message);
		flowId = FlowIdHelper.getFlowId(message);
		Assert.assertNotNull("FlowId should not be null after FlowIdProducerIn handleMessage()", flowId);
	}

	@Test
	public void flowIdProducerOutTest() {
		FlowIdProducerOut<Message> flowIdProducerOut = new FlowIdProducerOut<Message>();
		Message message = new MessageImpl();
		Exchange exchange = new ExchangeImpl();
		Message inMessage = new MessageImpl();
		exchange.setInMessage(inMessage);
		message.setExchange(exchange);
		
		String flowId = FlowIdHelper.getFlowId(message);
		Assert.assertNull("FlowId should be null before FlowIdProducerOut handleMessage()", flowId);
		flowIdProducerOut.handleMessage(message);
		flowId = FlowIdHelper.getFlowId(message);
		Assert.assertNotNull("FlowId should not be null after FlowIdProducerOut handleMessage()", flowId);
	}
}
