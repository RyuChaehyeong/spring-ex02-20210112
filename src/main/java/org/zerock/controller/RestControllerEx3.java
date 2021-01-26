package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rest3")
@Log4j
public class RestControllerEx3 {
	
	@RequestMapping("/ex1")
	public String method1(String name) {
		log.info("name: " + name);
		return "spring";
	}
	
	@RequestMapping("/ex2/{val}") //pathVariable
	public String method2(@PathVariable String val) {
		log.info("method2");
		log.info(val);
		return "method2";
	}
	
	@RequestMapping("/ex3/{val}")
	public String method3(@PathVariable String val) {
		log.info("method3");
		
		return val;
		
	}
	
	//주소에 정보가 붙어서 감
	@RequestMapping("/ex4/{val}/other/{age}")
	public String method4(@PathVariable String val, @PathVariable int age) {
		return val + age;
	
	}
	
	//몸통에 붙어서 정보가 간다. 
	@RequestMapping("/ex5")
	public String method5(@RequestBody String b) {
		log.info(b);
		return "method5";
	}
	
	
	//client에서 server로 json형식의 데이터 
	
	@RequestMapping("/ex6")
	//public String method6(@RequestBody String body) {
	public String method6(@RequestBody Rest1 body) {
		log.info(body);
		//여기에 {"name":"donald", "age":33, "vote":true, "obj":null}를 Rest1객체로 바꿔주면 조컷는디?
		//그러면 parameter를 String -> Rest1
		//결과는! Rest1(name=donald, age=33, vote=true, obj=null) json이 객체로 잘 변환이 된다!!!
		//객체 필드에 맞춰서 json 정보가 오면 객체로 바꾸어준다. 
		return "method6";
		
	}
	
	//consumes의 값은 mime type
	@RequestMapping(value = "/ex7", consumes = "text/plain")
	public String method7(@RequestBody String body) {
		log.info(body);
		
		return "method7";
	}
	
	//consumes의 값은 mime type
//	@RequestMapping(value = "/ex8", consumes = "application/json")
	@RequestMapping(value = "/ex8", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String method8(@RequestBody String body) {
		log.info(body);
		
		return "method8";
	}
	
	//consumes는 request header (Content-Type)과 연관이 있다.
	@RequestMapping(value = "/ex9", 
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, 
						MediaType.TEXT_PLAIN_VALUE})
	public String method9(@RequestBody String body) {
		log.info(body);
		
		return "method9";
	}
}
