package com.qisen.mts.common.util.handler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Long.class)
public class LongDateTypeHandler extends BaseTypeHandler<Long> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
		if (jdbcType != null && jdbcType.equals(JdbcType.DATE))
			ps.setDate(i, this.toSqlDate(parameter));
		else
			ps.setLong(i, parameter);
	}

	@Override
	public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getLong(columnName);
	}

	@Override
	public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getLong(columnIndex);
	}

	@Override
	public Long getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getLong(columnIndex);
	}

	private Date toSqlDate(Long parameter) {
		if (parameter != null) {
			return new Date(parameter);
		}
		return null;
	}

}
