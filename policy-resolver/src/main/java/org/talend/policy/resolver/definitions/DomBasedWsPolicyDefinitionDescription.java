package org.talend.policy.resolver.definitions;

import org.w3c.dom.Element;

/**
 * Specific Policy definition description providing a WS-Policy as DOM.
 */
public interface DomBasedWsPolicyDefinitionDescription extends PolicyDefinitionDescription {

	/**
	 * Get the root Element from which to read the Policy.
	 * @return root Element of policy definition.
	 */
	Element getPolicyElement();
}
