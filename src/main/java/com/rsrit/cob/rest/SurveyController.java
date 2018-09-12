package com.rsrit.cob.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsrit.cob.dto.ClaimStatisticsDto;
import com.rsrit.cob.dto.Cob_Claim;
import com.rsrit.cob.dto.Cob_Claim_Granular;
import com.rsrit.cob.dto.RecoveredClaimsDto;
import com.rsrit.cob.services.ClaimStatisticsDao;
import com.rsrit.cob.services.RecoveredClaimsDao;
import com.rsrit.cob.services.RecovererdClaimStatisticsDao;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

	private ClaimStatisticsDao claimStatsService;

	private RecovererdClaimStatisticsDao recoveredClaimStatisticsDao;

	private RecoveredClaimsDao recoveredClaimsDao;

	public SurveyController(ClaimStatisticsDao claimStatsService,
			RecovererdClaimStatisticsDao recoveredClaimStatisticsDao, RecoveredClaimsDao recoveredClaimsDao) {
		this.claimStatsService = claimStatsService;
		this.recoveredClaimStatisticsDao = recoveredClaimStatisticsDao;
		this.recoveredClaimsDao = recoveredClaimsDao;
	}

	// toDo: Code changes required

	@GetMapping
	public ClaimStatisticsDto getTheSurveyDetails() throws IOException {

		ClaimStatisticsDto dto = new ClaimStatisticsDto(this.claimStatsService.countByAgeGroups(),
				this.claimStatsService.countOfProvidersAndProvidersAmount(),
				this.claimStatsService.countOfMaleAndFemaleEntities(),
				this.claimStatsService.countOfClaimsMembersAmountForGroupAndIndividualMemberships(),
				this.claimStatsService.countOfClaimsMembersAmountForPrimaryAndDependantMemberships());

		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);

		return dto;
	}

	@GetMapping("/page2")
	public RecoveredClaimsDto getTheSurvetDetailsPage2() throws IOException {
		recoveredClaimStatisticsDao.countOfRecoveredClaimsAndRecoverableAmount();
		this.recoveredClaimStatisticsDao.countOfRecoverableReasonsAndAmounts();
		this.recoveredClaimStatisticsDao.countAndAmountOfRecoverableClaimsByMembershipType();

		return new RecoveredClaimsDto(recoveredClaimStatisticsDao.countOfRecoveredClaimsAndRecoverableAmount(),
				this.recoveredClaimStatisticsDao.countOfRecoverableReasonsAndAmounts(),
				this.recoveredClaimStatisticsDao.countAndAmountOfRecoverableClaimsByMembershipType());
	}

	@GetMapping("/page3")
	public List<List<Cob_Claim_Granular>> getTheSurvetDetailsPage3(@RequestParam("reason") String reason)
			throws IOException {
		System.out.println(reason);
		List<List<Cob_Claim_Granular>> resultList = new ArrayList<List<Cob_Claim_Granular>>();
		resultList.add(this.recoveredClaimsDao.claimsForReason(reason));
		// resultList.add(this.recoveredClaimsDao.claimsForEmployerSizeIsAcceptable());
		// resultList.add(this.recoveredClaimsDao.claimsForLineDateWithinStoppedWorkingEventDatesRange());
		return resultList;
	}

	@GetMapping("/claims/{claimId}")
	public Cob_Claim getTheClaimDetails(@PathVariable("claimId") String claimId) {
		return this.recoveredClaimsDao.getClaimById(claimId);
	}

	@GetMapping("/page4")
	public String getTheSurvetDetailsPage4() throws IOException {
		String data = null;
		InputStream cpath = new ClassPathResource("static/JF-5.json").getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(cpath));
		StringBuilder builder = new StringBuilder();
		while ((data = reader.readLine()) != null) {
			builder.append(data);
		}

		return builder.toString();
	}

}
