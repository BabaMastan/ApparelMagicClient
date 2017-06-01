package com.katalyst.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {
	@RequestMapping("/webShipments")
	public String angular(Model model) {
    	return "angular";
    }

}
