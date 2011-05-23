package org.talend.esb.sam.agent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.talend.esb.sam.common.event.Event;
import org.talend.esb.sam.common.event.EventTypeEnum;
import org.talend.esb.sam.common.service.MonitoringService;
import org.talend.esb.sam.common.util.EventCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/tesb/agent-context.xml")
public class EventGenerator {

	private static final String[] TRANSPORTS = {"http://schemas.xmlsoap.org/soap/http", "http://schemas.xmlsoap.org/rest"};
	
	@Autowired
	MonitoringService service;

	Random rnd = new Random(System.currentTimeMillis());

	EventCreator creator = new EventCreator();
	
	@Test
	public void testGeneration() throws Exception {
		for (int j = 0; j < 100; j++) {
			List<Event> events = new ArrayList<Event>();
			for (int i = 0; i < 100; i++) {
				events.addAll(newFlow(i));
			}
			service.putEvents(events);
			Thread.sleep(500 + rnd.nextInt(1000));
			System.out.println("Dumped 100");
		}
	}

	private Collection<Event> newFlow(int i) {
		Collection<Event> result = new ArrayList<Event>();
		Calendar cal = Calendar.getInstance();
		String flowID = UUID.randomUUID().toString();
		String operation = "{http://services.talend.org/CRMService}getCRMInformation";
		String portType = "{http://services.talend.org/CRMService}CRMService";
		String transport = rnd(TRANSPORTS);
		int processingTime = rnd.nextInt(10000);
		int networkDelay = rnd.nextInt(1000);
		result.add(creator.createEvent("content", cal.getTime(),
				EventTypeEnum.REQ_OUT, "orig_id", "local-consumer", "10.0.0.1",
				"1", flowID, UUID.randomUUID().toString(), operation, portType,
				transport));
		cal.add(Calendar.MILLISECOND, networkDelay);
		result.add(creator.createEvent("content", cal.getTime(),
				EventTypeEnum.REQ_IN, "orig_id", "local-provider", "10.0.0.1",
				"2", flowID, UUID.randomUUID().toString(), operation, portType,
				transport));
		if (rnd.nextInt(10) >= 5) {
			cal.add(Calendar.MILLISECOND, processingTime);
			result.add(creator.createEvent("content", cal.getTime(),
					EventTypeEnum.RESP_OUT, "orig_id", "local-provider",
					"10.0.0.1", "2", flowID, UUID.randomUUID().toString(),
					operation, portType, transport));
			cal.add(Calendar.MILLISECOND, networkDelay);
			result.add(creator.createEvent("content", cal.getTime(),
					EventTypeEnum.RESP_IN, "orig_id", "local-consumer", "10.0.0.1",
					"2", flowID, UUID.randomUUID().toString(), operation, portType,
					transport));
		}
		return result;
	}
	
	/**
	 * Returns one of the values randomly
	 * @param values
	 * @return
	 */
	private String rnd(String[] values) {
		return values[rnd.nextInt(values.length)];
	}

}
