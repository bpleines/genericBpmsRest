package com.companyName.RestTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;

public class RestRuntimeEngineProvider implements
		RuntimeEngineProvider {

	private String urlString;
	private String user;
	private String password;
	private String kieBase;
	private String kieSession;

	public RestRuntimeEngineProvider() {
		//TODO need to move these into configuration file, shouldn't be hardcoded
		//will look something like System.getProperty(systemPropertyName);
		this.urlString = "http://52.89.250.89:8080/business-central";
		this.user = "bpmOwner";
		this.password = "bpmsuite1!";
		this.kieBase = "";
		this.kieSession = "";
	}

	public RuntimeEngine getRuntimeEngine(String group, String artifact,
			String version) throws MalformedURLException {
		
		// Configuration specifying our local BPMS instance and Deployment
		URL deploymentUrl = new URL(urlString);

		// Get the KieSession
		StringBuilder deploymentIdBuilder = new StringBuilder();
		deploymentIdBuilder.append(group);
		deploymentIdBuilder.append(':');
		deploymentIdBuilder.append(artifact);
		deploymentIdBuilder.append(':');
		deploymentIdBuilder.append(version);

		if (kieBase != null && !kieBase.isEmpty() && kieSession != null
				&& !kieSession.isEmpty()) {
			deploymentIdBuilder.append(':');
			deploymentIdBuilder.append(kieBase);
			deploymentIdBuilder.append(':');
			deploymentIdBuilder.append(kieSession);
		}
		String deploymentId = deploymentIdBuilder.toString();

		/*
		 * RemoteRestRuntimeEngineFactory factory =
		 * RemoteRuntimeEngineFactory.newRestBuilder() .addUserName(user)
		 * .addPassword(password) .addDeploymentId(deploymentId)
		 * .addUrl(deploymentUrl) .buildFactory();
		 */

		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
				.addUrl(deploymentUrl).addUserName(user).addPassword(password)
				.addDeploymentId(deploymentId).build();
		
		return engine;
	}
}
