/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author juanm
 */
public class VendingMachineInventoryException extends Exception{
    public VendingMachineInventoryException(String message) {
        super(message);
    }
    
    public VendingMachineInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
