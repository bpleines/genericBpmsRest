package com.companyName.RestTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.NodeInstanceLog;
import org.kie.api.runtime.manager.audit.ProcessInstanceLog;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpgradeService implements UpgradeEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(UpgradeService.class);
	
	RestRuntimeEngineProvider runtimeEngineProvider = new RestRuntimeEngineProvider();

	// method to check the whether extended path of the application is functioning properly
	public Response healthCheck() {
		return Response.ok("Healthy!").build();
	}

	// method to start a process with the following parameters supplied from a url path
	public Response startProcess(String group, String artifact, String version,
			String processId) {
		LOG.info("REST request to start process "+processId+" in " + group + ":" + artifact + ":" + version);
		RuntimeEngine runtimeEngine = runtimeEngineProvider.getRuntimeEngine(group, artifact, version);
		KieSession kieSession = runtimeEngine.getKieSession();
		TaskService taskService = runtimeEngine.getTaskService();
		
		//start process with a generic inserted process variable String "checker"
		String checker = "Hello There!";
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("checker", checker);
		//ProcessInstance processInstance = kieSession.startProcess(processId, parameters);
		ProcessInstance processInstance = kieSession.startProcess(processId);
		long processInstanceId = processInstance.getId();
		String processInstanceIdString = Long.toString(processInstanceId);
		LOG.info("This processInstanceId is " + processInstanceId);
		LOG.info("The processInstanceId as a string is " + processInstanceIdString);
		//or start without the added process variable String "checker"
		//ProcessInstance processInstance = kieSession.startProcess(processId);

		// Code to let me know know is going on with the process
		if (processInstance.getState() == ProcessInstance.STATE_COMPLETED)
			return Response.ok("Process "+processId + "." +processInstanceId +" started and completed !").build();
		else if (processInstance.getState() == ProcessInstance.STATE_ACTIVE)
			return Response.ok("Process "+processId+ "." +processInstanceId + " started and is still active !").build();
		else if (processInstance.getState() == ProcessInstance.STATE_PENDING)
			return Response.ok("Process "+processId+ "." +processInstanceId + " started and is currently pending !").build();
		else if (processInstance.getState() == ProcessInstance.STATE_SUSPENDED)
			return Response.ok("Process "+processId+ "." +processInstanceId + " started and is currently suspended !").build();
		else if (processInstance.getState() == ProcessInstance.STATE_ABORTED)
			return Response.ok("Process "+processId+ "." +processInstanceId + " started but has aborted !").build();
		else
			return Response.serverError().build();
	}

	public Response sendSignal(String processId, String processInstanceIdString, String sendSignal) {
		LOG.info("REST request to sendSingal to process SignalTest");
		RuntimeEngine runtimeEngine = runtimeEngineProvider.getRuntimeEngine("example", "Test", "1.0");
		KieSession ksession = runtimeEngine.getKieSession();
		long processInstanceId = Long.valueOf(processInstanceIdString);
		String data = "Data!";
		ksession.signalEvent("fileReceived", data, processInstanceId);
		return Response.ok("Process " + processId + "." + processInstanceIdString + " was successfully signaled to continue executing").build();
	}

	public Response retrieveStatus(String group, String artifact,
			String version, String processId, String processInstanceIdString) {
		LOG.info("Checking to see updated status of " + group + "." + artifact + "." + version + "." + processId + "." + processInstanceIdString);
		RuntimeEngine runtimeEngine = runtimeEngineProvider.getRuntimeEngine("example", "Test", "1.0");
		KieSession ksession = runtimeEngine.getKieSession();
		long processInstanceId = Long.valueOf(processInstanceIdString);
		LOG.info("The processInstanceIdString is equal to " + processInstanceId);
		ProcessInstance processInstance = ksession.getProcessInstance(processInstanceId);
		
		//If the process instance is completed, then trying to get its state results in a NullPointerException, which is caught below
		try {
			if (processInstance.getState() == ProcessInstance.STATE_COMPLETED)
				return Response.ok("Process "+processId + "." +processInstanceId +" started and completed !").build();
			else if (processInstance.getState() == ProcessInstance.STATE_ACTIVE)
				return Response.ok("Process "+processId+ "." +processInstanceId + " started and is still active !").build();
			else if (processInstance.getState() == ProcessInstance.STATE_PENDING)
				return Response.ok("Process "+processId+ "." +processInstanceId + " started and is currently pending !").build();
			else if (processInstance.getState() == ProcessInstance.STATE_SUSPENDED)
				return Response.ok("Process "+processId+ "." +processInstanceId + " started and is currently suspended !").build();
			else if (processInstance.getState() == ProcessInstance.STATE_ABORTED)
				return Response.ok("Process "+processId+ "." +processInstanceId + " started but has aborted !").build();
			else
				return Response.serverError().build();
		}

		catch (NullPointerException e) {
			System.err.println("NullPointerException: " + e.getMessage());
			return Response.ok("Process "+processId+ "." + processInstanceId + " has likely completed given that a retrieval of its state results is null. Consult Business Central Process Instances under the Process Management tab to validate").build();
			
		}
		
	}

	public Response testPost(String jsonPostString) {
		LOG.info(jsonPostString);
		return Response.ok("DOm was here" + jsonPostString).build();
	
	}

}

