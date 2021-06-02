package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	public Item create() {
		LOGGER.info("please enter Item Name");
		String name = utils.getString();
		LOGGER.info("please enter the items value");
		Double value = utils.getDouble();
		Item item = itemDAO.create(new Item(name, value));
		LOGGER.info("Item added");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("please enter the Item Id for item being updated");
		long id = utils.getLong();
		LOGGER.info("please enter the new item name");
		String name = utils.getString();
		LOGGER.info("please enter new item value");
		Double value = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, value));
		LOGGER.info("Item has been updated succesfully");
		return item;

	}

	@Override
	public int delete() {
		LOGGER.info("please enter the Id for Item to delete");
		Long item_Id = utils.getLong();
		System.out.println("Item deleted");
		return itemDAO.delete(item_Id);

	}

}
