package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.ItemTest;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<ItemTest> {
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public List<ItemTest> readAll() {
		List<ItemTest> items = itemDAO.readAll();
		for (ItemTest item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	
	public ItemTest create() {
		LOGGER.info("please enter Item Name");
		String name = utils.getString();
		LOGGER.info("please enter the items value");
		Double value = utils.getDouble();
		ItemTest item = itemDAO.create(new ItemTest(name, value));
		LOGGER.info("Item added");
		return item;
	}

	@Override
	public ItemTest update() {
		LOGGER.info("please enter the Item Id for item being updated");
		long id = utils.getLong();
		LOGGER.info("please enter the new item name");
		String name = utils.getString();
		LOGGER.info("please enter new item value");
		Double value = utils.getDouble();
		ItemTest item = itemDAO.update(new ItemTest(id, name, value));
		LOGGER.info("Item has been updated succesfully");
		return item;

	}
	@Override
	public int delete() {
		LOGGER.info("please enter the Id for Item to delete");
		Long item_Id = utils.getLong();
		return itemDAO.delete(item_Id);

	}

}
