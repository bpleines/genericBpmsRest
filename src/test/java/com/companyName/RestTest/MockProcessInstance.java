package com.companyName.RestTest;

import org.kie.api.definition.process.Process;
import org.kie.api.runtime.process.ProcessInstance;

public class MockProcessInstance implements ProcessInstance {
	
	@Override
	public String getProcessId() {
		return "fake-process-id";
	}
	
	@Override
	public long getId() {
		return 101;
	}
	
	@Override
	public int getState() {
		return ProcessInstance.STATE_COMPLETED;
	}

	@Override
	public void signalEvent(String type, Object event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getEventTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Process getProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getParentProcessInstanceId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
