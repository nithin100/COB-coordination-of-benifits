package com.rsrit.cob.dto;

public class ClaimStatisticsDto {

	private String countByAgeGroups;
	private String countOfProvidersAndProvidersAmount;
	private String countOfMaleAndFemaleEntities;
	private String countOfClaimsMembersAmountForGroupAndIndividualMemberships;
	private String countOfClaimsMembersAmountForPrimaryAndDependantMemberships;

	public ClaimStatisticsDto(String countByAgeGroups, String countOfProvidersAndProvidersAmount,
			String countOfMaleAndFemaleEntities, String countOfClaimsMembersAmountForGroupAndIndividualMemberships,
			String countOfClaimsMembersAmountForPrimaryAndDependantMemberships) {
		super();
		this.countByAgeGroups = countByAgeGroups;
		this.countOfProvidersAndProvidersAmount = countOfProvidersAndProvidersAmount;
		this.countOfMaleAndFemaleEntities = countOfMaleAndFemaleEntities;
		this.countOfClaimsMembersAmountForGroupAndIndividualMemberships = countOfClaimsMembersAmountForGroupAndIndividualMemberships;
		this.countOfClaimsMembersAmountForPrimaryAndDependantMemberships = countOfClaimsMembersAmountForPrimaryAndDependantMemberships;
	}

	public String getCountByAgeGroups() {
		return countByAgeGroups;
	}

	public void setCountByAgeGroups(String countByAgeGroups) {
		this.countByAgeGroups = countByAgeGroups;
	}

	public String getCountOfProvidersAndProvidersAmount() {
		return countOfProvidersAndProvidersAmount;
	}

	public void setCountOfProvidersAndProvidersAmount(String countOfProvidersAndProvidersAmount) {
		this.countOfProvidersAndProvidersAmount = countOfProvidersAndProvidersAmount;
	}

	public String getCountOfMaleAndFemaleEntities() {
		return countOfMaleAndFemaleEntities;
	}

	public void setCountOfMaleAndFemaleEntities(String countOfMaleAndFemaleEntities) {
		this.countOfMaleAndFemaleEntities = countOfMaleAndFemaleEntities;
	}

	public String getCountOfClaimsMembersAmountForGroupAndIndividualMemberships() {
		return countOfClaimsMembersAmountForGroupAndIndividualMemberships;
	}

	public void setCountOfClaimsMembersAmountForGroupAndIndividualMemberships(
			String countOfClaimsMembersAmountForGroupAndIndividualMemberships) {
		this.countOfClaimsMembersAmountForGroupAndIndividualMemberships = countOfClaimsMembersAmountForGroupAndIndividualMemberships;
	}

	public String getCountOfClaimsMembersAmountForPrimaryAndDependantMemberships() {
		return countOfClaimsMembersAmountForPrimaryAndDependantMemberships;
	}

	public void setCountOfClaimsMembersAmountForPrimaryAndDependantMemberships(
			String countOfClaimsMembersAmountForPrimaryAndDependantMemberships) {
		this.countOfClaimsMembersAmountForPrimaryAndDependantMemberships = countOfClaimsMembersAmountForPrimaryAndDependantMemberships;
	}

}
