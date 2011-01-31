package org.talend.policy.resolver.helpers;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.cxf.ws.policy.AssertionBuilderRegistry;
import org.apache.cxf.ws.policy.PolicyBuilderImpl;
import org.apache.cxf.ws.policy.builder.primitive.PrimitiveAssertionBuilder;
import org.apache.cxf.ws.policy.builder.xml.XMLPrimitiveAssertionBuilder;

import org.apache.neethi.Policy;

import org.springframework.util.Assert;

import org.w3c.dom.Element;

public class WsPolicyBuilder  {

	private static final Log LOG = LogFactory.getLog(WsPolicyBuilder.class);

	private PolicyBuilderImpl policyBuilder;
	private PrimitiveAssertionBuilder assertionBuilder;

	public synchronized Policy buildPolicy(Element element) throws Exception {
		return policyBuilder.getPolicy(element);
	}

	public synchronized Policy buildPolicy(InputStream stream) throws Exception {
		return policyBuilder.getPolicy(stream);
	}

	public synchronized void registerPolicyAssertions(Collection<QName> assertions) {
		Collection<QName> assertionsToRegister = new LinkedList<QName>();
		Collection<QName> assertionsToUnregister = new LinkedList<QName>();

		if (assertionBuilder == null) {
			assertionBuilder = new XMLPrimitiveAssertionBuilder();
		} else {
			assertionsToUnregister.addAll(assertionBuilder.getKnownElements());
			assertionsToUnregister.removeAll(assertions);
		}
		assertionsToRegister.addAll(assertions);
		assertionBuilder.setKnownElements(assertionsToRegister);

		AssertionBuilderRegistry builderRegistry = policyBuilder.getAssertionBuilderRegistry();
		builderRegistry.setIgnoreUnknownAssertions(false);

		for (QName elem : assertionsToUnregister) {
			builderRegistry.unregister(elem);
		}

		for (QName elem : assertionsToRegister) {
			builderRegistry.register(elem, assertionBuilder);
		}
	}

	public synchronized void setPolicyBuilder(PolicyBuilderImpl policyBuilder) {
		this.policyBuilder = policyBuilder;
	}

	public PolicyBuilderImpl getPolicyBuilder() {
		return policyBuilder;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(policyBuilder, "Platform policy builder must be set");
	}

}
