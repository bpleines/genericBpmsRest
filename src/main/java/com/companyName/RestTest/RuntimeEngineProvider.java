package com.companyName.RestTest;

import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicReference;

import org.kie.api.runtime.manager.RuntimeEngine;


public interface RuntimeEngineProvider {

//	AtomicReference<RuntimeEngineProvider> HOLDER = new AtomicReference<>();
//	
//	static RuntimeEngineProvider get() {
//		
//		 while(true) {
//
//	            RuntimeEngineProvider instance = HOLDER.get();
//
//	            if (instance != null) {
//	                return instance;
//	            } else {
//	            	
//	            	instance = new RestRuntimeEngineProvider();
//	            }
//
//		 }
//	}
	
	RuntimeEngine getRuntimeEngine(String group, String artifact,
			String version) throws MalformedURLException;
	
}
