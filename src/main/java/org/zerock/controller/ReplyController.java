package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	
	@PostMapping(value="/new",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = {MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		log.info("vo: " + vo);
		int insertCount = service.register(vo);
		log.info("Reply insert count: " + insertCount);
		
		if (insertCount == 1) {
			return new ResponseEntity<>("success99", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE})
	
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("page")int page, 
			@PathVariable("bno") Long bno) {
		
		Criteria cri = new Criteria(page, 10);
		
		List<ReplyVO> list = service.getList(cri, bno);
		
		log.info(list);
		log.info(bno);
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/{rno}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		ReplyVO vo = service.get(rno);
		
		log.info(rno+"댓글입니다.");
		log.info(vo);
		
		return new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(
			@PathVariable("rno") Long rno) {
		
		int cnt = service.remove(rno);
		
		log.info(cnt);
		log.info(rno+ "삭제되었습니다.");
		
		if (cnt == 1) {
			return new ResponseEntity<String>("delete complete", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO vo,
			@PathVariable("rno") Long rno) {
		
		vo.setRno(rno);
		
		int cnt = service.modify(vo);
		log.info(cnt);
		
		if (cnt == 1) {
			return new ResponseEntity<String>("modify complete", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
