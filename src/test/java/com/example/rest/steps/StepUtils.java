package com.example.rest.steps;

import org.springframework.stereotype.Component;

@Component
public class StepUtils {
	
	//substitute by spring-mvc-test

//	protected static Client jerseyClient;
//
//	private static final String baseEndPoint = ResourceBundle.getBundle(
//			"stories-context").getString("baseEndPoint");
//
//	static {
//		DefaultClientConfig config = new DefaultClientConfig();
//		config.getClasses().add(JacksonJsonProvider.class);
//		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
//				Boolean.TRUE);
//		jerseyClient = Client.create(config);
//	}
//
//	public static ClientResponse loadPersonResource(String id,
//			String responseFormat) {
//		return jerseyClient.resource(baseEndPoint + "/person/" + id)
//				.accept(responseFormat).get(ClientResponse.class);
//	}

//	public static ClientResponse savePersonResource(Person person) {
//		return jerseyClient.resource(baseEndPoint + "/person").post(
//				ClientResponse.class, person);
//	}

}
