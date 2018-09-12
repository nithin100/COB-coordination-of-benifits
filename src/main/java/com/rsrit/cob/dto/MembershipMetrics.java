package com.rsrit.cob.dto;

/*
 * POJO for Membership Claims, Amount, Members and Membership type itself.  
 * 
 * */

public class MembershipMetrics {

	private Long claims;
	private Long members;
	private Object amount;
	private String membershipType;

	public void setClaims(Long claims) {
		this.claims = claims;
	}

	public void setMembers(Long members) {
		this.members = members;
	}

	public void setAmount(Object amount) {
		this.amount = amount;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public Long getClaims() {
		return claims;
	}

	public Long getMembers() {
		return members;
	}

	public Object getAmount() {
		return amount;
	}

	public String getMembershipType() {
		return membershipType;
	}

}
