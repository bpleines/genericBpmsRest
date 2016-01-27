package com.companyName.RestTest;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.task.TaskService;

public class MockRuntimeEngine implements RuntimeEngine {

	@Override
	public KieSession getKieSession() {
		return new MockKieSession();
	}

	@Override
	public TaskService getTaskService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditService getAuditService() {
		// TODO Auto-generated method stub
		return null;
	}

}
