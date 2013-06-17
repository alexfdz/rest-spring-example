package com.example.rest.commons.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.rest.commons.model.Endpoint;

@Controller
@RequestMapping(value= "/")
public class CatalogController {
	
	@Autowired
	protected RequestMappingHandlerMapping handlerMapping;
	
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody Map<String, Endpoint> getEndpointsCatalog() throws Throwable {
		Map<String, Endpoint> endpoints = new HashMap<String, Endpoint>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
		Set<RequestMappingInfo> mappingInfos = handlerMethods.keySet();
	
		for (RequestMappingInfo requestMappingInfo : mappingInfos) {
			addEndpoint(requestMappingInfo, endpoints);
		}
		return endpoints;
	}
	
	protected void addEndpoint(RequestMappingInfo requestMappingInfo, Map<String, Endpoint> endpoints){
		List<String> patterns = resolvePatterns(requestMappingInfo);
		for (String pattern : patterns) {
			Endpoint endpoint = null;
			if(endpoints.containsKey(pattern)){
				endpoint = endpoints.get(pattern);
			}else{
				endpoint = new Endpoint();
				endpoints.put(pattern, endpoint);
			}
			resolveEndpoint(requestMappingInfo, endpoint);
		}
	}
	
	protected void resolveEndpoint(RequestMappingInfo requestMappingInfo, Endpoint endpoint){
		endpoint.addMethods(resolveMethods(requestMappingInfo));
	}
	
	protected List<String> resolvePatterns(RequestMappingInfo requestMappingInfo){
		List<String> resolvedPatterns = new ArrayList<String>();
		Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
		if(patterns != null){
			for (String pattern : patterns) {
				resolvedPatterns.add(pattern);
			}
		}
		return resolvedPatterns;
	}
	
	protected List<String> resolveMethods(RequestMappingInfo requestMappingInfo){
		List<String> resolvedMethods = new ArrayList<String>();
		Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
		if(methods != null){
			for (RequestMethod method : methods) {
				resolvedMethods.add(method.name());
			}
		}
		return resolvedMethods;
	}
}
