package com.companyName.RestTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;

public class RestRuntimeEngineProvider {
	public RuntimeEngine getRuntimeEngine(String group, String artifact, String version) {
		//Configuration specifying our local BPMS instance and Deployment
				URL deploymentUrl = null;
				try {
					//52.89.250.89
					deploymentUrl = new URL("http://localhost:8080/business-central");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String user = "bpmOwner";
				String password = "bpmsuite1!";
				String kieBase = "";
				String kieSession = "";
				
				
				//Get the KieSession
				StringBuilder deploymentIdBuilder = new StringBuilder();
				deploymentIdBuilder.append(group);
				deploymentIdBuilder.append(':');
				deploymentIdBuilder.append(artifact);
				deploymentIdBuilder.append(':');
				deploymentIdBuilder.append(version);
				
				if (kieBase != null && !kieBase.isEmpty() &&
						kieSession != null && !kieSession.isEmpty()) {
					deploymentIdBuilder.append(':');
					deploymentIdBuilder.append(kieBase);
					deploymentIdBuilder.append(':');
					deploymentIdBuilder.append(kieSession);
				}
				String deploymentId = deploymentIdBuilder.toString();

/*				RemoteRestRuntimeEngineFactory factory = RemoteRuntimeEngineFactory.newRestBuilder()
						.addUserName(user)
						.addPassword(password)
						.addDeploymentId(deploymentId)
						.addUrl(deploymentUrl)
						.buildFactory();*/
				
		    	RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder().
		    			addUrl(deploymentUrl).
		    			addUserName(user).addPassword(password).
		    			addDeploymentId(deploymentId).build();
		    	
		return engine;
	}
}
