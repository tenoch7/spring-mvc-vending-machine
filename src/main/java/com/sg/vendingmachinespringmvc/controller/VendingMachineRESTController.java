/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juanm
 */
@RestController
public class VendingMachineRESTController {
    
    @Inject
    VendingMachineServiceLayer service;
    
}
