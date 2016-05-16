package com.isoftstone.study.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

@MappedTypes(UUID.class)
@Component
public class UUIDTypeHandler implements TypeHandler<UUID> {

	@Override
	public void setParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.toString());
	}

	@Override
	public UUID getResult(ResultSet rs, String columnName) throws SQLException {
		return StringUtils.isBlank(rs.getString(columnName)) ? null : UUID.fromString(rs.getString(columnName));
	}

	@Override
	public UUID getResult(ResultSet rs, int columnIndex) throws SQLException {
		return StringUtils.isBlank(rs.getString(columnIndex)) ? null : UUID.fromString(rs.getString(columnIndex));
	}

	@Override
	public UUID getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return StringUtils.isBlank(cs.getString(columnIndex)) ? null : UUID.fromString(cs.getString(columnIndex));
	}

}
