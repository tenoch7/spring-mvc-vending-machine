/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author juanm
 */
public interface VendingMachineServiceLayer {

    public List<Item> getAvailableItems() throws VendingMachinePersistenceException;
    
    public Item getOneItem(int itemId) throws VendingMachinePersistenceException, VendingMachineInventoryException;
    
    public boolean validateMoney(BigDecimal userMoney, int itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException;
    
    public BigDecimal dispatchItem(BigDecimal userMoney, Item item) throws VendingMachinePersistenceException;
    
    public String getChangeInCoints (BigDecimal change);
    
    public String getDeficit(BigDecimal userMoney, Item item) throws VendingMachinePersistenceException;
    
    public boolean matchUserChoiceWithItem(List<Item> availableItems, String userChoice) throws VendingMachinePersistenceException;
    
    public void addMoney(String money);
    
    public BigDecimal getMoney();
    
    public void setItemId(int itemId);
    
    public int getItemId();
    
    public String getChange();
    
    public void setMessage(boolean isMoneyValid);
    
    public String getMessage();
    
    public void setDeficit(String deficit);
    
    public String getDeficitMessage();
    
    
    
//    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
