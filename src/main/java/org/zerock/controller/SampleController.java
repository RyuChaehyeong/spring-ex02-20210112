package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	//1. 단순 문자열 반환
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";

	}
	
	
	//2. 객체의 반환
	
	@GetMapping(value="/getSample" , 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
						MediaType.APPLICATION_ATOM_XML_VALUE})
	
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
	
	
	//컬렉션 타입의 객체 반환 (리스트)
	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "first", i + "last"))
				.collect(Collectors.toList());
	}
	
	
	//컬렉션 타입의 객체 반환 (맵)
	
	@RequestMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	//ResponseEntity 타입: 데이터 자체를 전송하는 방식, HTTL 헤더의 상태 메시지 등을 함께 전달하는 용도로 사용
	
	//@RestContoller에서 파라미터
	
	@GetMapping(value="/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> checck(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		ResponseEntity<SampleVO> result = null;
		
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	

	
	
	//@PathVariable
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) {
		return new String[] {"category: " + cat, "productid: " + pid};
	}
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert....ticket" + ticket);
		
		return ticket;
	}
	//{"tno":6, "owner":"ch", "grade":"gold"}라고 request할때 body에 붙여주기

}
