package com.example.kafka.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.service.Producer;

@RestController
public class MainController {

	@Autowired
	private Producer producer;

	@PostMapping("/send")
	public ResponseEntity<?> sendMessage() {
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		producer.send("Hello World! " + isoFormat.format(new Date()));
		return ResponseEntity.ok().build();
	}
}
