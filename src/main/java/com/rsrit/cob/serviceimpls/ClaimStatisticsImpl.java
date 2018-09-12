package com.rsrit.cob.serviceimpls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsrit.cob.dto.MembershipMetrics;
import com.rsrit.cob.services.ClaimStatisticsDao;

/*
 * 
 * Service Implementation of Claim Stats.
 * 
 * */

@Service
public class ClaimStatisticsImpl implements ClaimStatisticsDao {

	private static final String queryForCountByAgeGroup = "SELECT COUNT(*) as #Persons, AgeGroup from (\r\n" + "\r\n"
			+ "select DATEDIFF(YEAR,psn_dob,GETDATE()) as AGE, '0 AND 17' AS AgeGroup  FROM HealthCare_COB_DB.dbo.person\r\n"
			+ "WHERE DATEDIFF(YEAR,psn_dob,GETDATE())  BETWEEN 0 AND 17\r\n" + "UNION ALL\r\n"
			+ "select DATEDIFF(YEAR,psn_dob,GETDATE()) as AGE, '18 AND 24'  FROM HealthCare_COB_DB.dbo.person\r\n"
			+ "WHERE DATEDIFF(YEAR,psn_dob,GETDATE())  BETWEEN 18 AND 24\r\n" + "UNION ALL\r\n"
			+ "select DATEDIFF(YEAR,psn_dob,GETDATE()) as AGE, '25 AND 44'  FROM HealthCare_COB_DB.dbo.person\r\n"
			+ "WHERE DATEDIFF(YEAR,psn_dob,GETDATE())  BETWEEN 25 AND 44\r\n" + "UNION ALL\r\n"
			+ "select DATEDIFF(YEAR,psn_dob,GETDATE()) as AGE, '45 AND 64'  FROM HealthCare_COB_DB.dbo.person\r\n"
			+ "WHERE DATEDIFF(YEAR,psn_dob,GETDATE())  BETWEEN 45 AND 64\r\n" + "UNION ALL\r\n"
			+ "select DATEDIFF(YEAR,psn_dob,GETDATE()) as AGE, '>65'  FROM HealthCare_COB_DB.dbo.person\r\n"
			+ "WHERE DATEDIFF(YEAR,psn_dob,GETDATE())  >65\r\n" + ")\r\n" + "a\r\n" + "group by AgeGroup";

	private static final String queryForCountOfNameAndCharge = "Select count(Distinct [pvd_name]), sum([charge_amt]) FROM [HealthCare_COB_DB].[dbo].[VW_RECOVERABLE_INFO]";

	private static final String queryForMaleAndFemaleData = "SELECT COUNT(DISTINCT [rel_psn_id]) as #Count,[gender] FROM HealthCare_COB_DB.dbo.person GROUP BY [gender]";

	private static final String QUERY_FOR_COUNT_OF_CLAIMS_MEMBERS_AMOUNT_BY_MEMBERSHIP_TYPE = "SELECT count([clm_id]) as #claims,count(DISTINCT[mbs_id_d])as #Members,sum([charge_amt])as amount,[mbs_type] from [HealthCare_COB_DB].[dbo].[VW_RECOVERABLE_INFO]\r\n"
			+ "WHERE clm_id IS NOT NULL group by [mbs_type]";

	private static final String QUERY_FOR_COUNT_OF_CLAIMS_MEMBERS_AMOUNT_FOR_PRIMARY_AND_DEPENDANT_MEMBERSHIPS = "Select count([clm_id]) as #claims,count(distinct [mbs_id_d])as #Members,sum([charge_amt])as amount,'PrimaryMember' as MemberType from [HealthCare_COB_DB].[dbo].[VW_RECOVERABLE_INFO]\r\n"
			+ "where mbs_id_d like '%-00' Union \r\n"
			+ "Select count([clm_id]) as #claims,count(distinct [mbs_id_d])as #Members,sum([charge_amt])as amount,'DependentMember'as MemberType from [HealthCare_COB_DB].[dbo].[VW_RECOVERABLE_INFO]\r\n"
			+ "where mbs_id_d not like '%-00'";

	JdbcTemplate jdbcTemplate;

	public ClaimStatisticsImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String countByAgeGroups() {
		//this.jdbcTemplate.execute(queryForCountByAgeGroup);
		List<Map<String, Object>> result = this.jdbcTemplate.queryForList(queryForCountByAgeGroup);
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
	public String countOfProvidersAndProvidersAmount() {

		Object resultMap = this.jdbcTemplate.query(queryForCountOfNameAndCharge, new StaticProviderDataCountMapper());

		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();

	}

	@Override
	public String countOfMaleAndFemaleEntities() {

		List<Object> resultMap = this.jdbcTemplate.query(queryForMaleAndFemaleData, new MaleFemaleCountRowMapper());

		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();
	}

	@Override
	public String countOfClaimsMembersAmountForGroupAndIndividualMemberships() {
		List<MembershipMetrics> membershipMetricList = this.jdbcTemplate
				.query(QUERY_FOR_COUNT_OF_CLAIMS_MEMBERS_AMOUNT_BY_MEMBERSHIP_TYPE, new MembershipDataRowMapper());
		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson
					.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(membershipMetricList));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();
	}

	@Override
	public String countOfClaimsMembersAmountForPrimaryAndDependantMemberships() {
		List<MembershipMetrics> membershipMetricList = this.jdbcTemplate.query(
				QUERY_FOR_COUNT_OF_CLAIMS_MEMBERS_AMOUNT_FOR_PRIMARY_AND_DEPENDANT_MEMBERSHIPS,
				new MembershipDataRowMapper());
		StringBuilder resultToJson = new StringBuilder();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		try {
			resultToJson
					.append(jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(membershipMetricList));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(resultToJson.toString());
		return resultToJson.toString();
	}

	static class StaticProviderDataCountMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("field1", rs.getLong(1));
			resultMap.put("field2", rs.getObject(2));
			return resultMap;
		}

	}

	static class MaleFemaleCountRowMapper implements RowMapper<Object> {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(rs.getString(2), rs.getObject(1));
			return resultMap;
		}

	}

	static class MembershipDataRowMapper implements RowMapper<MembershipMetrics> {

		@Override
		public MembershipMetrics mapRow(ResultSet rs, int rowNum) throws SQLException {
			MembershipMetrics membershipMetrics = new MembershipMetrics();
			membershipMetrics.setClaims(rs.getLong(1));
			membershipMetrics.setMembers(rs.getLong(2));
			membershipMetrics.setAmount(rs.getObject(3));
			membershipMetrics.setMembershipType(rs.getString(4));
			return membershipMetrics;
		}

	}

}
