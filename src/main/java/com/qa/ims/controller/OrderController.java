package com.qa.ims.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	static Scanner scanner = new Scanner(System.in);

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
		LOGGER.info("please enter id for customer order belongs to begin their order");
		Long id = utils.getLong();
	
		Order order = orderDAO.create(new Order(id));
		LOGGER.info("Order created successfully");
		return order;
	}

	@Override
	// ADD ITEM REMOVE ITEM
	public Order update() {
		
		String selection = "";
		boolean cont = true;

		do {
			
			LOGGER.info(
					"please enter (add) to add an entry of an items to order or (remove) to remove items entered to an order "
					+ "enter (total) to get checkout total or (done) to exit update");
			try {
				selection = scanner.nextLine();

				System.out.println("");
				switch (selection) {
				case "add":
					addItems();
					break;
				case "remove":
					removeItems();
					break;
				case "total":
					checkOut();
				case "done":
					cont = false;
					break;
				default:
					System.out.println("invalid selection");
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println(e);

			}
		} while (cont);

		return null;

		
	}

	private void checkOut() {
		LOGGER.info("please enter the Users Order ID to view the total checkout cost for that user");
		Long order_Id = utils.getLong();
		orderDAO.checkOut(order_Id);
		
	}

	private int removeItems() {
		LOGGER.info("please enter the Orders Items ID (basket Id)  to remove entry of items from an order ");
		Long oi_Id = utils.getLong();
		System.out.println("items have been removed");
		return orderDAO.removeItems(oi_Id);

	}

	private Order addItems() {
		LOGGER.info("please enter the order Id for the order being Updated");
		Long order_Id = utils.getLong();
		LOGGER.info("please enter the Item id for item being added");
		Long item_Id = utils.getLong();
		LOGGER.info("please enter the quantity of item selected to add from this order");
		Long quantity = utils.getLong();
		Order order = orderDAO.addItems(new Order(order_Id, item_Id,quantity));
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("please enter the Order Id for the order to delete");
		Long order_Id = utils.getLong();
		return orderDAO.delete(order_Id);
	}

}
