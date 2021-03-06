/*
 * #%L 
 * Service Activity Monitoring :: Server
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
package org.talend.esb.sam.server.persistence.dialects;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

/**
 * Interface to encapsulate difference between databases.
 *
 * @author zubairov
 */
public interface DatabaseDialect {

    String SUBSTITUTION_STRING = "%%FILTER%%";

    /**
     * Returns {@link DataFieldMaxValueIncrementer} for specific database.
     *
     * @return the incrementer
     */
    DataFieldMaxValueIncrementer getIncrementer();

    /**
     * Should return a query that list data.
     *
     * @param filter the filter
     * @return the data query
     */
    String getDataQuery(QueryFilter filter);

}
