package com.companyName.RestTest;

import java.net.MalformedURLException;

import org.kie.api.runtime.manager.RuntimeEngine;

public class MockRuntimeEngineProvider implements RuntimeEngineProvider{

	@Override
	public RuntimeEngine getRuntimeEngine(String group, String artifact,
			String version) throws MalformedURLException {
		return new MockRuntimeEngine();
	}

}
