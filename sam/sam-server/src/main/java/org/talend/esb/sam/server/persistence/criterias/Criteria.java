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
package org.talend.esb.sam.server.persistence.criterias;

/**
 * Criterias used for searching and filtering
 * 
 * @author zubairov
 */
public abstract class Criteria {
	
	protected final String name;
	
	protected final String columnName;
	
	public Criteria(String name, String colunmName) {
		this.name = name;
		this.columnName = colunmName;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Criteria) {
			return name.equals(((Criteria)obj).name);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * Creates a copy of the given {@link Criteria} populated
	 * with the parsed value or throws an exception 
	 * 
	 * @param attribute
	 * @return
	 */
	public abstract Criteria parseValue(String attribute);

	/**
	 * Returns a value of the Criteria that in suitable SQL type
	 *
	 * @return
	 */
	public abstract Object getValue();

	/**
	 * Returns something like (COLUMN = :name)
	 * @return
	 */
	public abstract StringBuilder getFilterClause();
}
