package com.foody.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;


@RestController
@RequestMapping(value ="/demo")
@RequestScope
public class Demo {
	
	@RequestMapping(value ="json",method = RequestMethod.GET)
	public Object jsonString() {
		
		List<String> list = new ArrayList<String>();
		list.add("Hung");
		list.add("Hung");
		list.add("Hung");
		list.add("Hung");
		list.add("Hung");
		return list;
	}

}
