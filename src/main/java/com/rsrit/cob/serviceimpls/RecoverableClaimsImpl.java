package com.rsrit.cob.serviceimpls;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsrit.cob.services.RecovererdClaimStatisticsDao;

@Service
public class RecoverableClaimsImpl implements RecovererdClaimStatisticsDao {
	JdbcTemplate jdbcTemplate;

	public RecoverableClaimsImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_AND_AMOUNTS = "SELECT COUNT(*) AS #RecoveredClaims, SUM([Charged_Amount]) as AmountRecoverable\r\n"
			+ "  FROM [HealthCare_COB_DB].[dbo].[Claims_Recovery_Info]";

	private static final String QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_FOR_REASON_WITH_AMOUNTS = " SELECT COUNT(*) AS #NUM,SUM([Charged_Amount]) as AmountRecoverable, [Recoverability_Reason]\r\n"
			+ "FROM [HealthCare_COB_DB].[dbo].[Claims_Recovery_Info]\r\n" + "GROUP BY [Recoverability_Reason]";

	private static final String QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_BY_MEMBERSHIP_WITH_AMOUNTS = "SELECT A.mbs_type,count(distinct A.Claim_Id) as #Claims,SUM([Charged_Amount]) as AmountRecovered\r\n"
			+ "  FROM \r\n"
			+ "(SELECT [Person_First_Name],[Person_Last_Name],[Age],[Gender],[Membership_Id],[Claim_Id]\r\n"
			+ "      ,[Recoverability],[Recoverability_Reason],[Charged_Amount]\r\n"
			+ "      ,[Net_Charged_Amount],[Provider_Name],[Execution_Time], mbs_type\r\n"
			+ "  FROM [HealthCare_COB_DB].[dbo].[Claims_Recovery_Info] INNER JOIN [HealthCare_COB_DB].[dbo].[membership]\r\n"
			+ "  ON [HealthCare_COB_DB].[dbo].[membership].mbs_id = SUBSTRING([Membership_Id], 0, len([Membership_Id])-2)\r\n"
			+ "  GROUP BY [Person_First_Name],[Person_Last_Name],[Age],[Gender],[Membership_Id],[Claim_Id],[Recoverability]\r\n"
			+ "      ,[Recoverability_Reason],[Charged_Amount],[Net_Charged_Amount],[Provider_Name],[Execution_Time],mbs_type\r\n"
			+ ")A\r\n" + "group by A.mbs_type";

	@Override
	public String countOfRecoveredClaimsAndRecoverableAmount() {
		// this.jdbcTemplate.execute(QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_AND_AMOUNT);
		List<Map<String, Object>> result = this.jdbcTemplate
				.queryForList(QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_AND_AMOUNTS);
		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();
	}

	@Override
	public String countOfRecoverableReasonsAndAmounts() {
		List<Map<String, Object>> result = this.jdbcTemplate
				.queryForList(QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_FOR_REASON_WITH_AMOUNTS);
		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();
	}

	@Override
	public String countAndAmountOfRecoverableClaimsByMembershipType() {
		List<Map<String, Object>> result = this.jdbcTemplate
				.queryForList(QUERY_FOR_COUNT_OF_RECOVERED_CLAIMS_BY_MEMBERSHIP_WITH_AMOUNTS);
		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();
	}

}
