package com.companyName.RestTest;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.Test;
import org.kie.api.runtime.process.ProcessInstance;

public class ProcessServiceTest {

	@Test
	public void kickOffProcessTest() throws MalformedURLException {
		ProcessService processService = new ProcessService(new MockRuntimeEngineProvider());
		
		ProcessInstance processInstance = processService.startProcess("test-group", "test-artifact", "test-version", "test-processid");
		assertEquals(processInstance.getId(), 101);
		assertEquals(processInstance.getState(), 2);
	}
	
}
