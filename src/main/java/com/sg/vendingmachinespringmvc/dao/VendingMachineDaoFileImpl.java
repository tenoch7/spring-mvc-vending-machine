/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author juanm
 */
@Component
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    File inventoryFile = new File(getClass().getClassLoader().getResource("inventory.txt").getFile());
    
//    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
//    private Map<String, Item> items = new HashMap<>();
    private Map<Integer, Item> items = new HashMap<>();
    
    

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems();
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(int itemId) throws VendingMachinePersistenceException {
        loadItems();
        return items.get(itemId);
    }

    @Override
    public Item removeItem(int itemId) throws VendingMachinePersistenceException {
        int currentAmount = items.get(itemId).getInventoryAmount();
        int removeOne = currentAmount - 1;
        items.get(itemId).setInventoryAmount(removeOne);
        writeItems();
        return items.get(itemId);
    }
//    @Override
//    public Item removeItem(String candyName) throws VendingMachinePersistenceException {
//        int currentAmount = items.get(candyName).getInventoryAmount();
//        int removeOne = currentAmount - 1;
//        items.get(candyName).setInventoryAmount(removeOne);
//        writeItems();
//        return items.get(candyName);
//    }

    @Override
    public void addItem(Item item) throws VendingMachinePersistenceException {
        items.put(item.getItemId(), item);
    }

    private void loadItems() throws VendingMachinePersistenceException {
        Scanner scanner = null;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        int idAdder = 1;
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(idAdder);
            currentItem.setCandyName(currentTokens[0]);
            currentItem.setPrice(new BigDecimal(currentTokens[1]));
            currentItem.setInventoryAmount(Integer.parseInt(currentTokens[2]));
            currentItem.setItemId(idAdder);
            idAdder++;

            items.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
    }

    private void writeItems() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }

        List<Item> itemsList = this.getAllItems();
        for (Item currentItem : itemsList) {
            out.println(currentItem.getCandyName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getInventoryAmount());
            out.flush();
        }
        out.close();
    }

}
