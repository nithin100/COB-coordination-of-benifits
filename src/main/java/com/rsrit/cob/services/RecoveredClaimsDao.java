package com.rsrit.cob.services;

import java.util.List;

import com.rsrit.cob.dto.Cob_Claim;
import com.rsrit.cob.dto.Cob_Claim_Granular;

/*
 * 
 * DAO for Recovered Claims. This is the DAO has all the operations for extracting recovered claims for 3 different reasons  
 * 
 * 
 * */

public interface RecoveredClaimsDao {

	List<Cob_Claim_Granular> claimsForReason(String reason);

	//List<Cob_Claim_Granular> claimsForMembershipTypeBeingIndividual();

	//List<Cob_Claim_Granular> claimsForEmployerSizeIsAcceptable();

	//List<Cob_Claim_Granular> claimsForLineDateWithinStoppedWorkingEventDatesRange();

	Cob_Claim getClaimById(String claimId);

}
