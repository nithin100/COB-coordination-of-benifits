package com.rsrit.cob.dto;

public class RecoveredClaimsDto {

	private String recoveredClaimsAndRecoverableAmount;

	private String recoverableReasonsAndAmounts;

	private String amountsOfRecoverableClaimsByMembershipType;

	public String getRecoveredClaimsAndRecoverableAmount() {
		return recoveredClaimsAndRecoverableAmount;
	}

	public String getRecoverableReasonsAndAmounts() {
		return recoverableReasonsAndAmounts;
	}

	public String getAmountsOfRecoverableClaimsByMembershipType() {
		return amountsOfRecoverableClaimsByMembershipType;
	}

	public RecoveredClaimsDto(String countOfRecoveredClaimsAndRecoverableAmount,
			String countOfRecoverableReasonsAndAmounts, String countAndAmountOfRecoverableClaimsByMembershipType) {
		super();
		this.recoveredClaimsAndRecoverableAmount = countOfRecoveredClaimsAndRecoverableAmount;
		this.recoverableReasonsAndAmounts = countOfRecoverableReasonsAndAmounts;
		this.amountsOfRecoverableClaimsByMembershipType = countAndAmountOfRecoverableClaimsByMembershipType;
	}

}
