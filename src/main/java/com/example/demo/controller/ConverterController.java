package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping(value = "/encode/file", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object encodeImage(@RequestParam MultipartFile image) {
		return converterService.encodeFile(image);
	}
	
	@GetMapping(value = "/decode/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> decodeImage(@RequestParam String base64Value, @RequestParam String name_with_extention) {
		return converterService.decodeFile(base64Value, name_with_extention);
	}

}
