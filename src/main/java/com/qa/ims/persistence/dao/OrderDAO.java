package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select c.id, c.first_name,c.surname,o.order_id,i.name,i.value\r\n"
						+ "from customers c \r\n" + " join orders o on c.id = o.id\r\n"
						+ " join orders_items oi on o.order_id = oi.order_id\r\n"
						+ " join items i on oi.item_id = i.item_id");) {

		} catch (SQLException x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}
		return new ArrayList<>();

	}

	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create(Order order) {	
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO orders(id,item_id) values (?,?)");){
					ps.setLong(1, order.getId());
					ps.setLong(2, order.getItem_Id());
					ps.executeUpdate();
					return readLatest();
						}catch (Exception x) {
							LOGGER.debug(x);
							LOGGER.error(x.getMessage());
						}
		return null;
	}

	private Order readLatest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {

		return null;
	}

}
