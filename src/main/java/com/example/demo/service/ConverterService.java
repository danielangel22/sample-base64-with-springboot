package com.example.demo.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import net.iharder.Base64;

@Service
public class ConverterService {

	public String encode(String value) {
		return Base64.encodeBytes(value != null ? value.getBytes() : null);

	}

	public String decode(String base64) {
		try {
			byte[] value = Base64.decode(base64);
			return new String(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
