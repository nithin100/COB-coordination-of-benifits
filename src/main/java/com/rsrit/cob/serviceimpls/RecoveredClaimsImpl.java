package com.rsrit.cob.serviceimpls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.rsrit.cob.dto.Cob_Claim;
import com.rsrit.cob.dto.Cob_Claim_Granular;
import com.rsrit.cob.services.RecoveredClaimsDao;

@Service
public class RecoveredClaimsImpl implements RecoveredClaimsDao {

	JdbcTemplate jdbcTemplate;

	public RecoveredClaimsImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String QUERY_FOR_CLAIM_DATA_FOR_MEMBERSHIP_TYPE_BEING_INDIVIDUAL = "SELECT *\r\n"
			+ "FROM [HealthCare_COB_DB].[dbo].[Claims_Recovery_Info]\r\n"
			+ "WHERE Recoverability_Reason = ?";

	/*private static final String QUERY_FOR_CLAIM_DATA_FOR_EMPLOYER_SIZE_IS_ACCEPTABLE = "SELECT *\r\n"
			+ "FROM [HealthCare_COB_DB].[dbo].[Claims_Recovery_Info]\r\n"
			+ "WHERE Recoverability_Reason LIKE  '%employer size is acceptable'";

	private static final String QUERY_FOR_CLAIM_DATA_FOR_LINE_DATE = "SELECT *\r\n"
			+ "FROM [HealthCare_COB_DB].[dbo].[Claims_Recovery_Info]\r\n"
			+ "WHERE Recoverability_Reason LIKE '%line date is within the Stopped working event dates range'";*/

	private static final String QUERY_FOR_CLAIM_DATA_BY_ID = "SELECT * \r\n"
			+ "FROM [HealthCare_COB_DB].[dbo].[VW_RECOVERABLE_INFO] \r\n" + "where clm_id = ?";

	@Override
	public List<Cob_Claim_Granular> claimsForReason(String reason) {
		return returnData(QUERY_FOR_CLAIM_DATA_FOR_MEMBERSHIP_TYPE_BEING_INDIVIDUAL, reason);
	}

	/*
	 * @Override public List<Cob_Claim_Granular>
	 * claimsForMembershipTypeBeingIndividual() { return
	 * returnData(QUERY_FOR_CLAIM_DATA_FOR_MEMBERSHIP_TYPE_BEING_INDIVIDUAL); }
	 * 
	 * @Override public List<Cob_Claim_Granular> claimsForEmployerSizeIsAcceptable()
	 * { return returnData(QUERY_FOR_CLAIM_DATA_FOR_EMPLOYER_SIZE_IS_ACCEPTABLE); }
	 * 
	 * @Override public List<Cob_Claim_Granular>
	 * claimsForLineDateWithinStoppedWorkingEventDatesRange() { return
	 * returnData(QUERY_FOR_CLAIM_DATA_FOR_LINE_DATE); }
	 */

	List<Cob_Claim_Granular> returnData(String query, String reason) {
		List<Cob_Claim_Granular> claimsList = this.jdbcTemplate.query(query, new Object[] { reason },
				new ListOfClaimsRowMapper());
		return claimsList;
	}

	@Override
	public Cob_Claim getClaimById(String claimId) {

		Cob_Claim claim = (Cob_Claim) this.jdbcTemplate.queryForObject(QUERY_FOR_CLAIM_DATA_BY_ID,
				new Object[] { claimId }, new ClaimDataRowMapper());
		return claim;
	}

	static class ClaimDataRowMapper implements RowMapper<Cob_Claim> {

		@Override
		public Cob_Claim mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Cob_Claim(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
					rs.getString(12), rs.getString(13), rs.getString(14), rs.getInt(15), rs.getDouble(16),
					rs.getDouble(17), rs.getInt(18), rs.getLong(19), rs.getString(20), rs.getString(21),
					rs.getString(22), rs.getString(23), rs.getInt(24), rs.getInt(25));
		}

	}

	static class ListOfClaimsRowMapper implements RowMapper<Cob_Claim_Granular> {

		@Override
		public Cob_Claim_Granular mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Cob_Claim_Granular(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getDouble(9), rs.getDouble(10),
					rs.getString(11), rs.getString(12));
		}

	}

}
