package com.katalyst.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katalyst.model.Shipment;
import com.katalyst.util.HttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
public class ClientwsController {
	private static final Logger logger = LoggerFactory.getLogger(ClientwsController.class);
	
	@RequestMapping(value="/test")
	public String info()
	{
		logger.debug("This is a test for logger creation");
		return "This app is Apparel Magic Client";
		
	}
	
	//This method is for getting all shipments
	@RequestMapping(value="/Shipments")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Shipment> Shipments()
	{
		
		JSONObject response = null;
		JSONObject shipment = null;
		JSONArray responsearray=null;
		ArrayList<Shipment> Shipments = null;
		try {
			response = HttpClient.sendto(null, "GET", "ship_methods?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			responsearray=(JSONArray)response.get("response");
			int j=responsearray.size();
			Shipments =new ArrayList<>();
			for(int i=0;i < j; i++)
			{
				shipment=(JSONObject) responsearray.get(i);
				Shipments.add((Shipment) JSONObject.toBean(shipment, Shipment.class));
				logger.debug( i +" Shipment :"+shipment.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     return Shipments;
		
	}
	
	@RequestMapping(value="/Shipments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shipment shipment(@PathVariable("id") long id){
		JSONObject response = null;
		JSONObject shipment = null;
		JSONArray responsearray=null;
		logger.debug("ID for path:"+id);
		System.out.println("ID for path:"+id);
		Map<Integer, Shipment> Shipments = null;
		try {
			response = HttpClient.sendto(null, "GET", "ship_methods?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			responsearray=(JSONArray)response.get("response");
			int j=responsearray.size();
			Shipments = new HashMap<Integer,Shipment>();
			Integer k;
			for(int i=0;i < j; i++)
			{
				shipment=(JSONObject) responsearray.get(i);
				//logger.debug( i +" Shipment :"+ shipment.toString());
				String Jid= (String) shipment.get("id");
				logger.debug("Jid:"+ Jid);
				k = new Integer(Integer.parseInt(Jid));
				logger.debug("K value is "+k);
				if(k==id){
					return (Shipment) JSONObject.toBean(shipment, Shipment.class);
				}
				Shipments.put(k, (Shipment) JSONObject.toBean(shipment, Shipment.class));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	     return null;
		
	}
		
	
	
	
}
