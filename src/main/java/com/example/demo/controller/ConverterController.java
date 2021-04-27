package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ConverterService;

@RestController
public class ConverterController {

	@Autowired
	private ConverterService converterService;

	@GetMapping(value = "/encode", produces = MediaType.APPLICATION_JSON_VALUE)
	public String encodeValue(@RequestParam String value) {
		return converterService.encode(value);
	}

	@GetMapping(value = "/decode", produces = MediaType.APPLICATION_JSON_VALUE)
	public String decodeValue(@RequestParam String base64Value) {
		return converterService.decode(base64Value);
	}

}
