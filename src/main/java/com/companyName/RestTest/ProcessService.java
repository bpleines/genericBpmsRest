package com.companyName.RestTest;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProcessService.class);
	
	private RuntimeEngineProvider runtimeEngineProvider;
	
	private static AtomicReference<ProcessService> HOLDER = new AtomicReference<>();

	public static ProcessService getInstance() {

		while (true) {

			ProcessService instance = HOLDER.get();

			if (instance != null) {
				return instance;
			}

			instance = new ProcessService();

			if (HOLDER.compareAndSet(null, instance)) {
				return instance;
			}
		}
	}
	
	public ProcessService() {
		this(new RestRuntimeEngineProvider());
	}
	
	// This constructor enables testing with a MockRuntimeEngineProvider
	public ProcessService(RuntimeEngineProvider runtimeEngineProvider){
		this.runtimeEngineProvider = runtimeEngineProvider;
	}
	
	public void setRuntimeEngineProvider(RuntimeEngineProvider runtimeEngineProvider){
		this.runtimeEngineProvider = runtimeEngineProvider;
	}
	
	public ProcessInstance startProcess(String group, String artifact, String version, String processId) throws MalformedURLException {
		LOG.info("Start process "+processId+" in " + group + ":" + artifact + ":" + version);
		RuntimeEngine runtimeEngine = runtimeEngineProvider.getRuntimeEngine(group, artifact, version);
		KieSession kieSession = runtimeEngine.getKieSession();
		TaskService taskService = runtimeEngine.getTaskService();
		
		//start process with a generic inserted process variable String "checker"
		String checker = "Hello There!";
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("checker", checker);
		//ProcessInstance processInstance = kieSession.startProcess(processId, parameters);
		return kieSession.startProcess(processId);
	}

}
