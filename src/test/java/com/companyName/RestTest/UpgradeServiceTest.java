package com.companyName.RestTest;

import java.net.MalformedURLException;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.kie.api.runtime.process.ProcessInstance;

import static org.junit.Assert.assertEquals;

public class UpgradeServiceTest {

	@Before
	public void setUpContext() {
		ProcessService processService = ProcessService.getInstance();
		processService.setRuntimeEngineProvider(new MockRuntimeEngineProvider());
	}
	
	@After
	public void destroyContext() {
		ProcessService processService = ProcessService.getInstance();
		processService.setRuntimeEngineProvider(new RestRuntimeEngineProvider());
	}

	@Test
	public void startProcessTest() throws MalformedURLException {
		UpgradeService upgradeService = new UpgradeService();
		Response response = upgradeService.startProcess("test-group",
				"test-artifact", "test-version", "test-processid");
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Process test-processid.101 started and completed !", response.getEntity().toString());
	}

}
