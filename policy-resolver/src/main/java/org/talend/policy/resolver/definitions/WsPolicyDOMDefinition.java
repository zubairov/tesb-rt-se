package org.talend.policy.resolver.definitions;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;


public class WsPolicyDOMDefinition implements
		DomBasedWsPolicyDefinitionDescription {

	private final QName serviceQName;

	private final Element policyElement;

	public WsPolicyDOMDefinition(final QName serviceQName,
			final Element policyElement) {

		this.serviceQName = serviceQName;
		this.policyElement = policyElement;
	}

	public QName getServiceQName() {
		return serviceQName;
	}

	public Element getPolicyElement() {
		return policyElement;
	}

}
