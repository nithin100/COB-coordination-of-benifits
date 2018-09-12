package com.rsrit.cob.services;

/*
 * DAO for Overall Recoverable Claims. This is the DAO which has all the operations for  
 * Page-2.
 * 
 */

public interface RecovererdClaimStatisticsDao {
	
	String countOfRecoveredClaimsAndRecoverableAmount();
	
	String countOfRecoverableReasonsAndAmounts();
	
	String countAndAmountOfRecoverableClaimsByMembershipType();

}
