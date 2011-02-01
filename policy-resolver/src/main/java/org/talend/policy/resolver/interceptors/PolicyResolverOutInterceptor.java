
package org.talend.policy.resolver.interceptors;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.Bus;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.ws.policy.AbstractPolicyInterceptor;
import org.apache.cxf.ws.policy.AssertionInfoMap;
import org.apache.cxf.ws.policy.EffectivePolicy;
import org.apache.cxf.ws.policy.EndpointPolicy;
import org.apache.cxf.ws.policy.PolicyAssertion;
import org.apache.cxf.ws.policy.PolicyBuilderImpl;
import org.apache.cxf.ws.policy.PolicyEngine;
import org.apache.cxf.ws.policy.PolicyInterceptorProvider;
import org.apache.cxf.ws.policy.PolicyInterceptorProviderRegistry;
import org.apache.cxf.ws.policy.PolicyOutInterceptor;
import org.apache.cxf.ws.policy.PolicyUtils;
import org.apache.cxf.ws.policy.PolicyVerificationOutInterceptor;
import org.apache.neethi.Policy;
import org.talend.policy.resolver.extractor.WsPolicyExtractor;
import org.talend.policy.resolver.definitions.PolicyDefinitionDescription;
import org.talend.policy.resolver.definitions.StreamBasedWsPolicyDefinitionDescription;
import org.talend.policy.resolver.definitions.provider.PolicyDefinitionProvider;
import org.xml.sax.SAXException;


/**
 * 
 */
public class PolicyResolverOutInterceptor extends AbstractPolicyInterceptor {
    public static final PolicyResolverOutInterceptor INSTANCE = new PolicyResolverOutInterceptor();
    
    private static final Logger LOG = LogUtils.getL7dLogger(PolicyResolverOutInterceptor.class);
    private PolicyDefinitionProvider policyDefinitionProvider ;
	private WsPolicyExtractor policyExtractor;
    
    public PolicyResolverOutInterceptor() {
        super(Phase.SETUP);
        addBefore(PolicyOutInterceptor.class.getName());
    }
    
    protected void handle(Message msg) {        
        Exchange exchange = msg.getExchange();
        Bus bus = exchange.get(Bus.class);
        
        BindingOperationInfo boi = exchange.get(BindingOperationInfo.class);
        if (null == boi) {
            LOG.fine("No binding operation info.");
            return;
        }
        
        Endpoint e = exchange.get(Endpoint.class);
        if (null == e) {
            LOG.fine("No endpoint.");
            return;
        }
        EndpointInfo ei = e.getEndpointInfo();
        
        PolicyEngine pe = bus.getExtension(PolicyEngine.class);
        if (null == pe) {
            return;
        }     

        if (MessageUtils.isRequestor(msg)) {
            Conduit conduit = exchange.getConduit(msg);
            
             // use same WS-policies as for application endpoint

            PolicyInterceptorProviderRegistry reg = bus
                .getExtension(PolicyInterceptorProviderRegistry.class);
            PolicyBuilderImpl pb = bus
            .getExtension(PolicyBuilderImpl.class);
            Policy p = null;
            Collection<PolicyDefinitionDescription> cpdp = policyDefinitionProvider.getPolicyDefinitions(ei.getName());

            for (PolicyDefinitionDescription desc : cpdp) {
            	StreamBasedWsPolicyDefinitionDescription policyDefinition = (StreamBasedWsPolicyDefinitionDescription)desc;
              	try {
					p= pb.getPolicy(policyDefinition.getPolicyStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
              	
    		}
            
            
            EndpointPolicy ep = null == conduit ? pe.getServerEndpointPolicy(ei, null) : pe.getClientEndpointPolicy(ei,
           		                                                               conduit);
            if (p != null)
            ep = ep.updatePolicy(p); 
            
            if (conduit != null) {
                pe.setClientEndpointPolicy(ei, ep);
            } else {
                pe.setServerEndpointPolicy(ei, ep);
            }

            EffectivePolicy effectiveOutbound = new EffectivePolicyImpl(ep, reg, true, false);
            EffectivePolicy effectiveInbound = new EffectivePolicyImpl(ep, reg, false, false);

            BindingInfo bi = ei.getBinding();
            Collection<BindingOperationInfo> bois = bi.getOperations();

            for (BindingOperationInfo bboi : bois) {
                pe.setEffectiveServerRequestPolicy(ei, bboi, effectiveInbound);
                pe.setEffectiveServerResponsePolicy(ei, bboi, effectiveOutbound);

                pe.setEffectiveClientRequestPolicy(ei, bboi, effectiveOutbound);
                pe.setEffectiveClientResponsePolicy(ei, bboi, effectiveInbound);
            }      
            
            // add the required interceptors
            EffectivePolicy effectivePolicy = pe.getEffectiveClientRequestPolicy(ei, boi, conduit);

            
            msg.put(EffectivePolicy.class, effectivePolicy);
            PolicyUtils.logPolicy(LOG, Level.FINEST, "Using effective policy: ", effectivePolicy.getPolicy());
            
            List<Interceptor<? extends Message>> interceptors = effectivePolicy.getInterceptors();
            for (Interceptor<? extends Message> i : interceptors) {            
                msg.getInterceptorChain().add(i);
                LOG.log(Level.FINE, "Added interceptor of type {0}", i.getClass().getSimpleName());
            }
            
            // insert assertions of the chosen alternative into the message
            
            Collection<PolicyAssertion> assertions = effectivePolicy.getChosenAlternative();
            if (null != assertions && !assertions.isEmpty()) {
                if (LOG.isLoggable(Level.FINEST)) {
                    StringBuilder buf = new StringBuilder();
                    buf.append("Chosen alternative: ");
                    String nl = System.getProperty("line.separator");
                    buf.append(nl);
                    for (PolicyAssertion a : assertions) {
                        PolicyUtils.printPolicyComponent(a, buf, 1);
                    }
                    LOG.finest(buf.toString());
                }
                msg.put(AssertionInfoMap.class, new AssertionInfoMap(assertions));
                msg.getInterceptorChain().add(PolicyVerificationOutInterceptor.INSTANCE);
            }
        } else {
            Destination destination = exchange.getDestination();
            EffectivePolicy effectivePolicy = pe.getEffectiveServerResponsePolicy(ei, boi, destination);
            msg.put(EffectivePolicy.class, effectivePolicy);
            
            List<Interceptor<? extends Message>> interceptors = effectivePolicy.getInterceptors();
            for (Interceptor<? extends Message> oi : interceptors) {
                msg.getInterceptorChain().add(oi);
                LOG.log(Level.FINE, "Added interceptor of type {0}",
                        oi.getClass().getSimpleName());           
            }
            
            // insert assertions of the chosen alternative into the message
                 
            Collection<PolicyAssertion> assertions = effectivePolicy.getChosenAlternative();
            if (null != assertions && !assertions.isEmpty()) {
                msg.put(AssertionInfoMap.class, new AssertionInfoMap(assertions));
                msg.getInterceptorChain().add(PolicyVerificationOutInterceptor.INSTANCE);
            }
        }
    }
 	public void setPolicyDefinitionProvider(PolicyDefinitionProvider pdp) {
		this.policyDefinitionProvider = pdp;
	}

	public PolicyDefinitionProvider getPolicyDefinitionProvider() {
		return policyDefinitionProvider;
	}
	class EffectivePolicyImpl implements EffectivePolicy {

        private EndpointPolicy endpointPolicy;
        private List<Interceptor<? extends Message>> interceptors;

        EffectivePolicyImpl(EndpointPolicy ep, PolicyInterceptorProviderRegistry reg, boolean outbound,
                            boolean fault) {
            endpointPolicy = ep;
            interceptors = reg.getInterceptors(endpointPolicy.getChosenAlternative(), outbound, fault);
        }

        public Collection<PolicyAssertion> getChosenAlternative() {
            return endpointPolicy.getChosenAlternative();
        }

        public List<Interceptor<? extends Message>> getInterceptors() {
            return interceptors;
        }

        public Policy getPolicy() {
            return endpointPolicy.getPolicy();
        }
    }
}
