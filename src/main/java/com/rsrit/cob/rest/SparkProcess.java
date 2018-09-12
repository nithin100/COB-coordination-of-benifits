package com.rsrit.cob.rest;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rsrit.cob.utils.SparkProcessService;

@RestController
@RequestMapping("/api/survey")
public class SparkProcess {

	private SparkProcessService sparkService;

	public SparkProcess(SparkProcessService sparkService) {
		this.sparkService = sparkService;
	}

	// toDO: Change the status from list to ConcurrentHashMap

	@PostMapping("/process/execute")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void processExecute(HttpServletResponse response, UriComponentsBuilder builder, Principal user) {
		// System.out.println(user.getName());
		this.sparkService.executeCompletableProcess();

		String status = builder.path("/api/survey/process/status").build().encode().toString();
		response.setHeader("location", status);
	}

	@GetMapping("/process/status")
	public String processExecutionStatus(HttpServletResponse response) {
		return sparkService.getStatus();
	}

}
