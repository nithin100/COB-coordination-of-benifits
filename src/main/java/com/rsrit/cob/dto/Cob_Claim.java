package com.rsrit.cob.dto;

public class Cob_Claim {

	private String person_First_Name;

	private String person_Last_Name;

	private String person_Dob;

	private int person_Age;

	private String person_Gender;

	private String claim_Id;

	private int line_Number;

	private String line_Date;

	private String event_Names;

	private String event_StartDates;

	private String event_EndDates;

	private String membership_Type;

	private String membership_Id;

	private String primary_Member;

	private int primacy;

	private Double charge_Amount;

	private Double netPaidAmount;

	private int proc_medicare_covered;

	private Long provider_Id;

	private String provider_Name;

	private String provider_Zipcode;

	private String part_Type;

	private String emp_tin;

	private int emp_size_aged;

	private int emp_size_disabled;

	public Cob_Claim(String person_First_Name, String person_Last_Name, String person_Dob, int person_Age,
			String person_Gender, String claim_Id, int line_Number, String line_Date, String event_Names,
			String event_StartDates, String event_EndDates, String membership_Type, String membership_Id,
			String primary_Member, int primacy, Double charge_Amount, Double netPaidAmount, int proc_medicare_covered,
			Long provider_Id, String provider_Name, String provider_Zipcode, String part_Type, String emp_tin,
			int emp_size_aged, int emp_size_disabled) {

		this.person_First_Name = person_First_Name;
		this.person_Last_Name = person_Last_Name;
		this.person_Dob = person_Dob;
		this.person_Age = person_Age;
		this.person_Gender = person_Gender;
		this.claim_Id = claim_Id;
		this.line_Number = line_Number;
		this.line_Date = line_Date;
		this.event_Names = event_Names;
		this.event_StartDates = event_StartDates;
		this.event_EndDates = event_EndDates;
		this.membership_Type = membership_Type;
		this.membership_Id = membership_Id;
		this.primary_Member = primary_Member;
		this.primacy = primacy;
		this.charge_Amount = charge_Amount;
		this.netPaidAmount = netPaidAmount;
		this.proc_medicare_covered = proc_medicare_covered;
		this.provider_Id = provider_Id;
		this.provider_Name = provider_Name;
		this.provider_Zipcode = provider_Zipcode;
		this.part_Type = part_Type;
		this.emp_tin = emp_tin;
		this.emp_size_aged = emp_size_aged;
		this.emp_size_disabled = emp_size_disabled;
	}

	public String getPerson_First_Name() {
		return person_First_Name;
	}

	public String getPerson_Last_Name() {
		return person_Last_Name;
	}

	public String getPerson_Dob() {
		return person_Dob;
	}

	public int getPerson_Age() {
		return person_Age;
	}

	public String getPerson_Gender() {
		return person_Gender;
	}

	public String getClaim_Id() {
		return claim_Id;
	}

	public int getLine_Number() {
		return line_Number;
	}

	public String getLine_Date() {
		return line_Date;
	}

	public String getEvent_Names() {
		return event_Names;
	}

	public String getEvent_StartDates() {
		return event_StartDates;
	}

	public String getEvent_EndDates() {
		return event_EndDates;
	}

	public String getMembership_Type() {
		return membership_Type;
	}

	public String getMembership_Id() {
		return membership_Id;
	}

	public String getPrimary_Member() {
		return primary_Member;
	}

	public int getPrimacy() {
		return primacy;
	}

	public Double getCharge_Amount() {
		return charge_Amount;
	}

	public Double getNetPaidAmount() {
		return netPaidAmount;
	}

	public int getProc_medicare_covered() {
		return proc_medicare_covered;
	}

	public Long getProvider_Id() {
		return provider_Id;
	}

	public String getProvider_Name() {
		return provider_Name;
	}

	public String getProvider_Zipcode() {
		return provider_Zipcode;
	}

	public String getPart_Type() {
		return part_Type;
	}

	public String getEmp_tin() {
		return emp_tin;
	}

	public int getEmp_size_aged() {
		return emp_size_aged;
	}

	public int getEmp_size_disabled() {
		return emp_size_disabled;
	}

}
