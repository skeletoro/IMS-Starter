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
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_Id = resultSet.getLong("order_id");
		Long id = resultSet.getLong("id");
		return new Order(order_Id, id);
	}

	public Order modelFromResultSetBulk(ResultSet resultSetB) throws SQLException {
		Long order_Id = resultSetB.getLong("order_id");
		String first_Name = resultSetB.getString("first_name");
		Long id = resultSetB.getLong("id");
		Long item_Id = resultSetB.getLong("item_id");
		Long quantity = resultSetB.getLong("quantity");
		return new Order(order_Id, first_Name, id, item_Id, quantity);
	}

	public double resultSetCheckout(ResultSet rSetCheckOut) throws SQLException {
		double total = rSetCheckOut.getDouble("SUM(i.value*oi.quantity)");
		LOGGER.info("order total is for this order is $" + total);
		return total;
	}

	public Order idResultSet(ResultSet rSID) throws SQLException {
		Long id = rSID.getLong("id");
		return new Order(id);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement s = connection.createStatement();
				ResultSet resultSetBulk = s.executeQuery(
						"select c.id, c.first_name,c.surname,o.order_id,i.item_id,i.name,i.value, oi.quantity from customers c join orders o on c.id = o.id join orders_items oi on o.order_id = oi.order_id join items i on oi.item_id = i.item_id ORDER BY order_id");) {
			List<Order> orders = new ArrayList<>();
			while (resultSetBulk.next()) {
				orders.add(modelFromResultSetBulk(resultSetBulk));
			}
			return orders;
		} catch (SQLException x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}
		return new ArrayList<>();

	}

	@Override
	public Order read(Long id) {
//		try(Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement ps = connection.prepareStatement(arg0)
		return null;
	}

	// works
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO orders(id) values (?)");) {
			ps.setLong(1, order.getId());
			ps.executeUpdate();
			return readLatest();
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}
		return null;
	}

	// works
	private Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			rs.next();
			return modelFromResultSet(rs);
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}
		return null;
	}

	// works
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE orders SET id = ? WHERE order_id = ?");) {
			ps.setLong(1, order.getId());
			ps.setLong(2, order.getOrder_Id());
			ps.executeUpdate();
			return read(order.getOrder_Id());
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}
		return null;
	}

	// works
	@Override
	public int delete(long order_Id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			ps.setLong(1, order_Id);
			return ps.executeUpdate();
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return 0;
	}

	// works
	public Order addItems(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("insert into orders_items (order_id,item_id,quantity) Values (?,?,?)");) {
			ps.setLong(1, order.getOrder_Id());
			ps.setLong(2, order.getItem_Id());
			ps.setLong(3, order.getQuantity());
			ps.executeUpdate();
			System.out.println("items added");

		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return null;
	}

//works
	public int removeItems(long oi_Id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("Delete FROM orders_items where oi_id = ?");) {
			ps.setLong(1, oi_Id);
			ps.executeUpdate();

		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return 0;
	}

	public double checkOut(Long order_Id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT sum(i.value*oi.quantity) FROM  items i join orders_items oi ON i.item_id = oi.item_id WHERE oi.order_id = (?)");) {
			statement.setLong(1, order_Id);
			ResultSet rsco = statement.executeQuery();
			rsco.next();
			return resultSetCheckout(rsco);

		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return 0;
	}

}
