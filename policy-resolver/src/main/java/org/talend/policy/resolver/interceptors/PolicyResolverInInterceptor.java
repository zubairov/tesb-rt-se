
package org.talend.policy.resolver.interceptors;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.cxf.Bus;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.ws.policy.AbstractPolicyInterceptor;
import org.apache.cxf.ws.policy.AssertionInfoMap;
import org.apache.cxf.ws.policy.EffectivePolicy;
import org.apache.cxf.ws.policy.EffectivePolicyImpl;
import org.apache.cxf.ws.policy.EndpointPolicy;
import org.apache.cxf.ws.policy.EndpointPolicyImpl;
import org.apache.cxf.ws.policy.PolicyAssertion;
import org.apache.cxf.ws.policy.PolicyConstants;
import org.apache.cxf.ws.policy.PolicyEngine;
import org.apache.cxf.ws.policy.PolicyEngineImpl;
import org.apache.cxf.ws.policy.PolicyInInterceptor;
import org.apache.cxf.ws.policy.PolicyUtils;
import org.apache.cxf.ws.policy.PolicyVerificationInInterceptor;
import org.apache.neethi.Policy;

/**
 * 
 */
public class PolicyResolverInInterceptor extends AbstractPolicyInterceptor {
    public static final PolicyResolverInInterceptor INSTANCE = new PolicyResolverInInterceptor();
    
    private static final Logger LOG = LogUtils.getL7dLogger(PolicyResolverInInterceptor.class);
    
    public PolicyResolverInInterceptor() {
        super(Phase.RECEIVE);
        addBefore(PolicyInInterceptor.class.getName());
    }
    
    protected void handle(Message msg) {
        Exchange exchange = msg.getExchange();
        Bus bus = exchange.get(Bus.class);
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
            
            BindingOperationInfo boi = exchange.get(BindingOperationInfo.class);
            Policy p = (Policy)msg.getContextualProperty(PolicyConstants.POLICY_OVERRIDE);
            if (p != null) {
                EndpointPolicyImpl endpi = new EndpointPolicyImpl(p);
                EffectivePolicyImpl effectivePolicy = new EffectivePolicyImpl();
       //         effectivePolicy.initialise(endpi, (PolicyEngineImpl)pe, true);
                msg.put(EffectivePolicy.class, effectivePolicy);
                PolicyUtils.logPolicy(LOG, Level.FINEST, "Using effective policy: ", 
                                      effectivePolicy.getPolicy());
                
                List<Interceptor<? extends Message>> interceptors = effectivePolicy.getInterceptors();
                for (Interceptor<? extends Message> i : interceptors) {            
                    msg.getInterceptorChain().add(i);
                    LOG.log(Level.FINE, "Added interceptor of type {0}", i.getClass().getSimpleName());
                }
                Collection<PolicyAssertion> assertions = effectivePolicy.getChosenAlternative();
                if (null != assertions && !assertions.isEmpty()) {
                    msg.put(AssertionInfoMap.class, new AssertionInfoMap(assertions));
                    msg.getInterceptorChain().add(PolicyVerificationInInterceptor.INSTANCE);
                }
            } else if (boi == null) {
                Conduit conduit = exchange.getConduit(msg);
            
                EndpointPolicy ep = pe.getClientEndpointPolicy(ei, conduit);
                
                List<Interceptor<? extends Message>> interceptors = ep.getInterceptors();
                if (null != interceptors) {
                    for (Interceptor<? extends Message> i : interceptors) {
                        msg.getInterceptorChain().add(i);
                    }
                }
                
                // insert assertions of endpoint's vocabulary into message
                
                Collection<PolicyAssertion> assertions = ep.getVocabulary();
                if (null != assertions && !assertions.isEmpty()) {
                    msg.put(AssertionInfoMap.class, new AssertionInfoMap(assertions));
                    msg.getInterceptorChain().add(PolicyVerificationInInterceptor.INSTANCE);
                }
            } else {
                // We do not know the underlying message type yet - so we pre-emptively add interceptors 
                // that can deal with any resposes or faults returned to this client endpoint.
                
                EffectivePolicy ep = pe.getEffectiveClientResponsePolicy(ei, boi);
        
                List<Interceptor<? extends Message>> interceptors = ep.getInterceptors();
                if (null != interceptors) {
                    for (Interceptor<? extends Message> i : interceptors) {
                        msg.getInterceptorChain().add(i);
                    }
                }
                // insert assertions of endpoint's vocabulary into message
                if (ep.getPolicy() != null) {
                    msg.put(AssertionInfoMap.class, new AssertionInfoMap(ep.getPolicy()));
                    msg.getInterceptorChain().add(PolicyVerificationInInterceptor.INSTANCE);
                }
            }
        } else {            
            Destination destination = exchange.getDestination();
            
            // We do not know the underlying message type yet - so we pre-emptively add interceptors 
            // that can deal with any messages to this endpoint
            
            EndpointPolicy ep = pe.getServerEndpointPolicy(ei, destination);
            
            List<Interceptor<? extends Message>> interceptors = ep.getInterceptors();
            if (null != interceptors) {
                for (Interceptor<? extends Message> i : interceptors) {
                    msg.getInterceptorChain().add(i);
                    LOG.log(Level.FINE, "Added interceptor of type {0}", i.getClass().getSimpleName());
                }
            }
            
            // insert assertions of endpoint's vocabulary into message
            
            Collection<PolicyAssertion> assertions = ep.getVocabulary();
            if (null != assertions && !assertions.isEmpty()) {
                msg.put(AssertionInfoMap.class, new AssertionInfoMap(assertions));
                msg.getInterceptorChain().add(PolicyVerificationInInterceptor.INSTANCE);
            }
        }
    }
}
