package org.talend.policy.resolver.extractor;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.neethi.Policy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.talend.policy.resolver.definitions.DomBasedWsPolicyDefinitionDescription;
import org.talend.policy.resolver.definitions.PolicyDefinitionDescription;
import org.talend.policy.resolver.definitions.StreamBasedWsPolicyDefinitionDescription;
import org.talend.policy.resolver.definitions.WsPolicyDOMDefinition;
import org.talend.policy.resolver.definitions.WsPolicyStreamDefinition;
import org.talend.policy.resolver.helpers.WsPolicyBuilder;
import org.w3c.dom.Element;

public class WsPolicyExtractor implements InitializingBean {

	private WsPolicyBuilder policyBuilder;

	public WsPolicyExtractor() {
		super();
	}

	public Policy extractPolicy(Element element) {
		Policy policy =
			doExtractPolicy(new WsPolicyDOMDefinition(null, element));
		return policy;
	}

	public Policy extractPolicy(InputStream inputStream) {
		Policy policy =
			doExtractPolicy(new WsPolicyStreamDefinition(null, inputStream));
		return policy;
	}

	public Policy extractPolicy(
			PolicyDefinitionDescription policyDefinition) {
		if (policyDefinition != null) {
			if (policyDefinition instanceof StreamBasedWsPolicyDefinitionDescription) {
				return doExtractPolicy(
						(StreamBasedWsPolicyDefinitionDescription) policyDefinition);
			}
			if (policyDefinition instanceof DomBasedWsPolicyDefinitionDescription) {
				return doExtractPolicy(
						(DomBasedWsPolicyDefinitionDescription) policyDefinition);
			}
		}
		throw new IllegalArgumentException("Bad policy definition argument provided. ");
	}

	public Class<Policy> getPlatformPolicyType() {
		return Policy.class;
	}

	public boolean isSupportedType(
			final Class<? extends PolicyDefinitionDescription> type) {
		return StreamBasedWsPolicyDefinitionDescription.class.isAssignableFrom(type) ||
				DomBasedWsPolicyDefinitionDescription.class.isAssignableFrom(type);
	}

	private Policy doExtractPolicy(
			StreamBasedWsPolicyDefinitionDescription policyDefinition) {
		try {
			configurePolicyBuilder();
	        final Policy policy = policyBuilder.buildPolicy(policyDefinition.getPolicyStream());
			return policy;
		} catch (Exception e) {
			throw new RuntimeException("Corrupted policy. ", e);
		}
	}

	private Policy doExtractPolicy(
			DomBasedWsPolicyDefinitionDescription policyDefinition) {
		try {
			configurePolicyBuilder();
	        final Policy policy = policyBuilder.buildPolicy(policyDefinition.getPolicyElement());

	        return policy;
		} catch (Exception e) {
			throw new RuntimeException("Corrupted policy. ", e);
		}
	}

	private void configurePolicyBuilder() {
		Set<QName> knownElements = new HashSet<QName>();
		
//		for (Interceptor interceptor : planner.getRegisteredInterceptors()) {
//			Map<String, ?> props = interceptor.getProperties();
//			if (props.containsKey(Interceptor.)
//					&& props.get(Interceptor.TYPE_PROPERTY) instanceof QName) {
//				knownElements.add((QName) (props.get(Interceptor.TYPE_PROPERTY)));
//			}
//		}
		policyBuilder.registerPolicyAssertions(knownElements);
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(policyBuilder, "Policy builder must be initialized");
	}

	public WsPolicyBuilder getPolicyBuilder() {
		return policyBuilder;
	}

	public void setPolicyBuilder(WsPolicyBuilder policyBuilder) {
		this.policyBuilder = policyBuilder;
	}

}
