package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("please enter id for customer order belongs to");
		Long id = utils.getLong();
		LOGGER.info("please enter Item Id for selected item");
		Long item_Id = utils.getLong();
		Order order = orderDAO.create(new Order(id, item_Id));
		LOGGER.info("Order created successfully");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("please enter the order Id for the order being Updated");
		Long order_Id = utils.getLong();
		LOGGER.info("please enter Id for customer this is for");
		Long id = utils.getLong();
		LOGGER.info("please enter the item Id for the new item in this order");
		Long item_Id = utils.getLong();
		Order order = orderDAO.update(new Order(order_Id, id, item_Id));
		LOGGER.info("Order Updated");

		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("please enter the Order Id for the order to delete");
		Long order_Id = utils.getLong();
		return orderDAO.delete(order_Id);
	}

}
