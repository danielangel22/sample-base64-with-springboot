package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.iharder.Base64;

/**
 * The Class ConverterService.
 */
@Service
public class ConverterService {

	/** The local. */
	private static String local = "src/main/resources/";

	/**
	 * Encode.
	 *
	 * @param value the value
	 * @return the string
	 */
	public String encode(String value) {
		return Base64.encodeBytes(value != null ? value.getBytes() : null);
	}

	/**
	 * Decode.
	 *
	 * @param base64 the base 64
	 * @return the string
	 */
	public String decode(String base64) {
		try {
			byte[] value = Base64.decode(base64);
			return new String(value);
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	/**
	 * this method is applicable for any type of file
	 * 
	 * @param imFile
	 * @return
	 */
	public Object encodeFile(MultipartFile imFile) {
		try {
			var path = Path.of(local.concat(imFile.getOriginalFilename()));
			if (!Files.exists(path)) {
				Files.copy(imFile.getInputStream(), path);
			}
			var response = new HashMap<>();
			response.put("name_with_extention", imFile.getOriginalFilename());
			response.put("base64", Base64.encodeFromFile(path.toString()));
			return response;
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	/**
	 * this method is applicable for any type of file
	 * 
	 * @param base64
	 * @param nameWithExtention
	 * @return File
	 */
	public ResponseEntity<byte[]> decodeFile(String base64, String nameWithExtention) {
		try {
			byte[] image = Base64.decode(base64);
			//Files.write(Path.of(local.concat(nameWithExtention)), image); optional save local file
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
					ContentDisposition.attachment().filename(nameWithExtention).build().toString());
			return ResponseEntity.ok().headers(httpHeaders).body(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
