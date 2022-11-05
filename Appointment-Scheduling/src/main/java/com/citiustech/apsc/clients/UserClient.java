package com.citiustech.apsc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="USERMANAGEMENTADMINISTRATION",url = "http://localhost:9500/user")
@Service
public interface UserClient {
	
	@GetMapping("/{username}")
	public Object getUser(String username);
	
	@GetMapping("/hi")
	public String sayHello();
}

