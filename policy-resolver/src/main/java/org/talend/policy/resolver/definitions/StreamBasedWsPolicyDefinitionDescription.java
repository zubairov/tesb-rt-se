package org.talend.policy.resolver.definitions;

import java.io.InputStream;

/**
 * Specific Policy definition description providing a WS-Policy as Stream.
 */
public interface StreamBasedWsPolicyDefinitionDescription extends PolicyDefinitionDescription {

	/**
	 * Get the Stream from which to read the Policy.
	 * @return Policy definition as Stream.
	 */
	InputStream getPolicyStream();
}
