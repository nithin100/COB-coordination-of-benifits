package com.rsrit.cob.utils;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SparkProcessService {

	List<String> statusList = new ArrayList<String>();
	
	/*@PostConstruct 
	public void someMethod() {
		System.out.println("Creating a new object");
	}*/

	@Async
	public Future<String> executeCompletableProcess() {
		//System.out.println("adding status");
		statusList.add("Execution started");
		//System.out.println("adding status");
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(Redirect.appendTo(new File(System.getProperty("user.dir") + "/log.txt")));
		processBuilder.command("execute.bat");

		try {
			Process processOfExecution = processBuilder.start();
			try {
				int exitVal = processOfExecution.waitFor();
				if (exitVal == 0) {
					statusList.remove(0);
					statusList.add("Execution successfully finished");
					System.out.println("Done..!");
				} else {
					statusList.remove(0);
					statusList.add("Execution failed");
					// System.out.println("Nope..!");
				}
				return new AsyncResult<String>("Process ended");
			} catch (InterruptedException in) {
				statusList.add("Execution failed");
				in.printStackTrace();
			}

		} catch (IOException e) {
			statusList.add("Execution failed");
			e.printStackTrace();
		}
		return new AsyncResult<String>("Process started");
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		if (statusList.size() != 0) {
			if (statusList.get(0).equals("Execution successfully finished")
					|| statusList.get(0).equals("Execution failed")) {
				return statusList.remove(0);
			} else {
				return statusList.get(0);
			}
		} else {
			return "No process is running!";
		}
	}

}
