/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author juanm
 */
@Service
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private BigDecimal currentMoney = BigDecimal.ZERO;
    private Integer itemSelected = 0;
    private String strChange = "";
    private String message = "";
    private String deficitMessage = "";
            
    @Inject
    VendingMachineDao dao;

    @Override
    public List<Item> getAvailableItems() throws VendingMachinePersistenceException {
        //filters inventory to oly display items with InventoryAmount greater than zero.
        return dao.getAllItems().stream().filter(i -> i.getInventoryAmount() > 0).collect(Collectors.toList());
    }

    @Override
    public Item getOneItem(int itemId) throws VendingMachinePersistenceException, VendingMachineInventoryException {
        if (dao.getItem(itemId) == null) {
            throw new VendingMachineInventoryException("Item not on inventory.");
        } else {
            return dao.getItem(itemId);
        }
    }

    @Override
    public boolean validateMoney(BigDecimal userMoney, int itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException {

        int moneyVsPrice = userMoney.compareTo(dao.getItem(itemId).getPrice());
        if (moneyVsPrice == 0 || moneyVsPrice == 1)/*User money is equal or greater than price*/ {
            return true;
        } else /*Price is greater than user Money*/ {
            throw new VendingMachineInsufficientFundsException("Insuficcient funds.");

        }
    }

    @Override
    public BigDecimal dispatchItem(BigDecimal userMoney, Item item) throws VendingMachinePersistenceException {
        BigDecimal change = userMoney.subtract(item.getPrice());
        dao.removeItem(item.getItemId());
        currentMoney = BigDecimal.ZERO;
        itemSelected = 0;
        strChange = "";
        return change;
    }

    @Override
    public String getChangeInCoints(BigDecimal change) {
        change = change.multiply(BigDecimal.valueOf(100));
        BigDecimal quarters[] = change.divideAndRemainder(BigDecimal.valueOf(25));
        int quarterCoins = quarters[0].intValue();

        BigDecimal dimes[] = quarters[1]/*quarters remainder*/.divideAndRemainder(BigDecimal.valueOf(10));
        int dimeCoins = dimes[0].intValue();

        BigDecimal nickels[] = dimes[1].divideAndRemainder(BigDecimal.valueOf(5));
        int nickelCoins = nickels[0].intValue();

        int pennyCoins = nickels[1].intValue();
        strChange = "Q: " + quarterCoins + " D: " + dimeCoins + " N: " + nickelCoins + " P: " + pennyCoins;
        
        message = "Money returned!";
        currentMoney = BigDecimal.ZERO;
        itemSelected = 0;
        return strChange;
    }

    @Override
    public String getDeficit(BigDecimal userMoney, Item item) throws VendingMachinePersistenceException {

        BigDecimal moneyNeeded = item.getPrice().subtract(userMoney);
//        String deficit = ("Sorry, " + item.getCandyName() + " costs $" + item.getPrice()
//                + ".\nYou only entered $" + userMoney + ". You are $" + moneyNeeded + " short.");
        String deficit = moneyNeeded.toString();
        return deficit;
    }

    @Override
    public boolean matchUserChoiceWithItem(List<Item> availableItems, String userChoice) throws VendingMachinePersistenceException {
        boolean match = availableItems.stream().anyMatch(i -> i.getCandyName().equals(userChoice));
        return match;
    }

    @Override
    public void addMoney(String money) {
        
        BigDecimal moneyAdded = new BigDecimal(money);
        currentMoney = currentMoney.add(moneyAdded);
    }

    @Override
    public BigDecimal getMoney() {
        return currentMoney;
    }

    @Override
    public void setItemId(int itemId) {
        itemSelected = itemId;
    }

    @Override
    public int getItemId() {
        return itemSelected;
    }

    @Override
    public String getChange() {
        return strChange;
    }

    @Override
    public void setMessage(boolean isMoneyValid) {
        message = "";
        if (isMoneyValid == true) {
            message = "Thank you!!!";
        } else {
            message = "Please insert: ";
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setDeficit(String deficit) {
        deficitMessage = deficit;
    }

    @Override
    public String getDeficitMessage() {
        return deficitMessage;
    }
    

}
