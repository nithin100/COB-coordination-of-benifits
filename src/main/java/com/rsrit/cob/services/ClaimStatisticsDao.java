package com.rsrit.cob.services;

/*
 * DAO for Overall Claim Statistics. This is the DAO which has all the operations for  
 * Page-1.
 * 
 */

public interface ClaimStatisticsDao {

	String countByAgeGroups();
	
	String countOfProvidersAndProvidersAmount();
	
	String countOfMaleAndFemaleEntities();
	
	String countOfClaimsMembersAmountForGroupAndIndividualMemberships();
	
	String countOfClaimsMembersAmountForPrimaryAndDependantMemberships();

}
