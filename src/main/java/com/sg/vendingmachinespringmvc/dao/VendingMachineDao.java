/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;

/**
 *
 * @author juanm
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(int itemId) throws VendingMachinePersistenceException;
    
    Item removeItem(int itemId) throws VendingMachinePersistenceException;
    
    void addItem(Item item) throws VendingMachinePersistenceException;
    
    
    
}
