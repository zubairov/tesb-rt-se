package org.talend.policy.resolver.definitions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.namespace.QName;

public class WsPolicyStreamDefinition implements
		StreamBasedWsPolicyDefinitionDescription {

	private final QName serviceQName;

	private final InputStream policyStream;

	public WsPolicyStreamDefinition(final QName serviceQName,
			final InputStream policyStream) {

		this.serviceQName = serviceQName;
		this.policyStream = policyStream;
	}

	public WsPolicyStreamDefinition(final QName serviceQName,
			final byte[] policyData) {

		this.serviceQName = serviceQName;
		this.policyStream = new ByteArrayInputStream(policyData);
	}

	public QName getServiceQName() {
		return serviceQName;
	}

	public InputStream getPolicyStream() {
		return policyStream;
	}
}
