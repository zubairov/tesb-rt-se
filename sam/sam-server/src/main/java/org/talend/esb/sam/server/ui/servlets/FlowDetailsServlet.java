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
package org.talend.esb.sam.server.ui.servlets;

import javax.servlet.http.HttpServletRequest;

import org.talend.esb.sam.server.ui.UIProvider;

import com.google.gson.JsonObject;

/**
 * API Service that returns flow details.
 *
 * @author telesh
 */
public class FlowDetailsServlet extends AbstractAPIServlet {

    /**
     *
     */
    private static final long serialVersionUID = 4001052811324863157L;

    /* (non-Javadoc)
     * @see org.talend.esb.sam.server.ui.servlets.AbstractAPIServlet#process(javax.servlet.http.HttpServletRequest, org.talend.esb.sam.server.ui.UIProvider)
     */
    @Override
    JsonObject process(HttpServletRequest req, UIProvider provider) throws Exception {
        String requestURI = req.getRequestURI();
        String flowID = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        JsonObject result = provider.getFlowDetails(flowID, getBaseUrl(req));
        if (null == result) {
            throw new NotFoundException("Can't find flow with ID: " + flowID);
        }
        return result;
    }
}
