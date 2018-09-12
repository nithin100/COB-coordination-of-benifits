package com.rsrit.cob.dto;

public class Cob_Claim_Granular {

	private String person_First_Name;

	private String person_Last_Name;

	private int person_Age;

	private String person_Gender;

	private String membership_Id;

	private String claim_Id;

	private int recoverability;

	private String recoverability_Reason;

	private Double charge_Amount;

	private Double netChargedAmount;

	private String provider_Name;

	private String execution_Time;

	public Cob_Claim_Granular(String person_First_Name, String person_Last_Name, int person_Age, String person_Gender,
			String membership_Id, String claim_Id, int recoverability, String recoverability_Reason,
			Double charge_Amount, Double netChargedAmount, String provider_Name, String execution_Time) {
		super();
		this.person_First_Name = person_First_Name;
		this.person_Last_Name = person_Last_Name;
		this.person_Age = person_Age;
		this.person_Gender = person_Gender;
		this.membership_Id = membership_Id;
		this.claim_Id = claim_Id;
		this.recoverability = recoverability;
		this.recoverability_Reason = recoverability_Reason;
		this.charge_Amount = charge_Amount;
		this.netChargedAmount = netChargedAmount;
		this.provider_Name = provider_Name;
		this.execution_Time = execution_Time;
	}

	public String getPerson_First_Name() {
		return person_First_Name;
	}

	public String getPerson_Last_Name() {
		return person_Last_Name;
	}

	public int getPerson_Age() {
		return person_Age;
	}

	public String getPerson_Gender() {
		return person_Gender;
	}

	public String getMembership_Id() {
		return membership_Id;
	}

	public String getClaim_Id() {
		return claim_Id;
	}

	public int getRecoverability() {
		return recoverability;
	}

	public String getRecoverability_Reason() {
		return recoverability_Reason;
	}

	public Double getCharge_Amount() {
		return charge_Amount;
	}

	public Double getNetChargedAmount() {
		return netChargedAmount;
	}

	public String getProvider_Name() {
		return provider_Name;
	}

	public String getExecution_Time() {
		return execution_Time;
	}

}
