/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author juanm
 */
@Controller
public class VendingMachineController {

    @Inject
    VendingMachineServiceLayer service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getItems(Model model) {

        try {
            model.addAttribute("items", service.getAvailableItems());
            model.addAttribute("currentMoney", service.getMoney());
            model.addAttribute("candyId", service.getItemId());
            model.addAttribute("change", service.getChange());
            model.addAttribute("message", service.getMessage());
            model.addAttribute("deficitMessage", service.getDeficitMessage());
        } catch (VendingMachinePersistenceException ex) {
            System.out.println("Inventory.txt not found" + ex);
        }

        return "index";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(String amount, Model model) {

        service.addMoney(amount);
        return "redirect:/";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(String candyId, Model model) {

        service.setItemId(Integer.parseInt(candyId));

        return "redirect:/";
    }

    @RequestMapping(value = "/dispatchItem", method = RequestMethod.GET)
    public String dispatchItem(String candyId, String currentMoney, Model model) {

        int itemId = Integer.parseInt(candyId);
        BigDecimal bDCurrentMoney = new BigDecimal(currentMoney);
        String deficit = "";

        boolean isMoneyValid = false;
        try {
            isMoneyValid = service.validateMoney(bDCurrentMoney, itemId);
        } catch (VendingMachinePersistenceException | VendingMachineInsufficientFundsException ex) {
            System.out.println(ex);
        }

        if (isMoneyValid == true) {
            BigDecimal change = BigDecimal.ZERO;
            try {
                change = service.dispatchItem(bDCurrentMoney, service.getOneItem(itemId));
            } catch (VendingMachineInventoryException | VendingMachinePersistenceException ex) {
                System.out.println("Item not found" + ex);

            }

            service.getChangeInCoints(change);
        } else {
            try {
                deficit = service.getDeficit(bDCurrentMoney, service.getOneItem(itemId));
            } catch (VendingMachinePersistenceException | VendingMachineInventoryException ex) {
                System.out.println(ex);
            }
        }
        service.setDeficit(deficit);
        service.setMessage(isMoneyValid);

        return "redirect:/";
    }

    @RequestMapping(value = "/cancelTransaction", method = RequestMethod.GET)
    public String returnMoney(String currentMoney) {
        BigDecimal bDCurrentMoney = new BigDecimal(currentMoney);
        service.getChangeInCoints(bDCurrentMoney);
        return "redirect:/";
    }

}
