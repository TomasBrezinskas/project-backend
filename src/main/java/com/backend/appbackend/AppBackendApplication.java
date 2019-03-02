package com.backend.appbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@SpringBootApplication
public class AppBackendApplication {

	@RequestMapping("/hello")
	@ResponseBody
	public HashMap<String, String> returnHello() {
		HashMap<String, String> map = new HashMap<>();
		map.put("hello", "world");
		return map;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppBackendApplication.class, args);
	}

}
