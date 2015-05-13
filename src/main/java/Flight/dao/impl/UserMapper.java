package Flight.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Flight.model.UserNotification;

public class UserMapper implements RowMapper {

	@Override
	public UserNotification mapRow(ResultSet rs, int rowNumber) throws SQLException {
		UserNotification user = new UserNotification();
		user.setNotifyDate(rs.getDate("notify_date"));
		user.setNotifyTime(rs.getTime("notify_time"));
		user.setUserID(rs.getString("user_id"));
		user.setDepartureDate(rs.getString("departure_date"));
		user.setDepartureTime(rs.getString("departure_time"));
		user.setWaitTime(rs.getString("wait_time"));
		user.setFlightNumber(rs.getString("flight_number"));
		return user;
	}

}
