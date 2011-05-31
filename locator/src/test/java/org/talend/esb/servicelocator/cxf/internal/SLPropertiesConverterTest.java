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

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.xml.namespace.NamespaceContext;

import org.junit.Test;
import org.talend.esb.servicelocator.NamespaceContextImpl;
import org.talend.esb.servicelocator.client.SLPropertiesImpl;
import org.talend.esb.servicelocator.client.internal.endpoint.EntryType;
import org.talend.esb.servicelocator.client.internal.endpoint.ServiceLocatorPropertiesType;

public class SLPropertiesConverterTest {

    public static final String KEY_1 = "key1";

    public static final String KEY_2 = "key2";

    public static final String VALUE_1 = "value1";

    public static final String VALUE_2 = "value2";

    public static final String VALUE_3 = "value3";

    public static final NamespaceContext Sl_NS_CONTEXT =
        new NamespaceContextImpl("sl" , "http://talend.org/tesb/serviceLocator");

    @Test
    public void slProperties2JAXBSlPropertiesType() throws Exception {
        SLPropertiesImpl props = new SLPropertiesImpl();
        props.addMultiProperty(KEY_1, VALUE_1, VALUE_2);
        props.addMultiProperty(KEY_2, VALUE_2, VALUE_3);
        
        ServiceLocatorPropertiesType jaxbProperties = SLPropertiesConverter.toServiceLocatorPropertiesType(props);
        List<EntryType> entries = jaxbProperties.getEntry();
        List<String> values =  entries.get(0).getValue();
        assertEquals(VALUE_1, values.get(0));
    }
}
