package org.talend.policy.resolver.helpers;

import java.net.URL;

import org.springframework.core.io.Resource;

public class ResourceToStringConverter {

    private Resource resource;
    private String url;

    public ResourceToStringConverter(final String resource) {
    	final URL resUrl = getClass().getClassLoader().getResource(resource);
    	if (resUrl == null) {
    		throw new RuntimeException(
    				"Resource " + resource + " not found. ");
    	}
        url = resUrl.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(final Resource resource) {
        this.resource = resource;
    }
}
