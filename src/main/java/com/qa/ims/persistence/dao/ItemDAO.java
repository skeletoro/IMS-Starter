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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long item_Id = resultSet.getLong("item_id");
		String name = resultSet.getString("name");
		double value = resultSet.getDouble("value");
		return new Item(item_Id, name,value);

	}

	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM items");) {
			List<Item> items = new ArrayList<>();
			while (rs.next()) {
				items.add(modelFromResultSet(rs));
			}
			return items;
		} catch (SQLException s) {
			LOGGER.debug(s);
			LOGGER.error(s.getMessage());
		}
		return new ArrayList<>();

	}

	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			rs.next();
			return modelFromResultSet(rs);
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return null;
	}

	@Override
	public Item read(Long item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM items WHERE item_id = ?");) {
			ps.setLong(1, item);
			try (ResultSet rs = ps.executeQuery();) {
				rs.next();
				return modelFromResultSet(rs);
			}

		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return null;
	}

	
	
	@Override
	public  Item create(Item item ) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement s = connection.prepareStatement("INSERT INTO items(name,value) VALUES (?, ?)");) {
			s.setString(1, item.getName());
			s.setDouble(2, item.getValue());
			s.executeUpdate();
			return readLatest();
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}
		return null;

	}

	@Override
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE items SET name = ? ,value = ? WHERE item_id = ?");) {
			ps.setString(1, item.getName());
			ps.setDouble(2, item.getValue());
			ps.setLong(3, item.getItem_Id());
			ps.executeUpdate();
			return read(item.getItem_Id());

		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return null;

	}

	@Override
	public int delete(long item_Id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM items WHERE item_id");) {
			ps.setLong(1, item_Id);
			return ps.executeUpdate();
		} catch (Exception x) {
			LOGGER.debug(x);
			LOGGER.error(x.getMessage());
		}

		return 0;
	}

}
