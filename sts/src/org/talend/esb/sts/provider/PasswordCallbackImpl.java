package org.talend.esb.sts.provider;

import java.io.IOException;
import java.io.StringReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PasswordCallbackImpl implements CallbackHandler {
	private static final Log LOG = LogFactory
	.getLog(PasswordCallbackImpl.class.getName());
	
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        
/*        try {
			throw new Exception(pc.getIdentifier());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(e);
		}
        if (pc.getIdentifier().equals("joe")) {
            // set the password on the callback. This will be compared to the
            // password which was sent from the client.
            pc.setPassword("password");
        }  else {
        	pc.setPassword("password");
        }*/
        GlobalUser.setUserName(pc.getIdentifier());
        GlobalUser.setUserPassword(pc.getPassword());
    }
}
