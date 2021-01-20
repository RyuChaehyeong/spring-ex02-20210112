package org.zerock.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CommentVO;
import org.zerock.mapper.CommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentServiceTests {

	//@Setter(onMethod_ =@Autowired)
	private CommentService service;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	public void setService(CommentService service) {
		this.service = service;
	}
	
	@Setter(onMethod_ =@Autowired)
	private CommentMapper mapper;
	
	//bean으로 잘 만들어져서 injection 되었는지 확인
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("내용입눼다");
		comment.setWriter("채형입니닷");
		
		int before = mapper.getList().size();
		service.register(comment);
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);
	}
	
	
	@Test
	public void testGetList() {
		List<CommentVO> list = service.getList();
		
		assertNotNull(list);
		assertNotEquals(list.size(), 0);
	}
	
	@Test
	public void testGet() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("내용입눼다");
		comment.setWriter("채형입니닷");
			
		service.register(comment);

		CommentVO vo = service.get(comment.getCno());
		assertNotNull(vo);
		assertEquals(vo.getCno(), comment.getCno());
		assertEquals(vo.getContent(), comment.getContent());
	}
	
	@Test
	public void testDelete() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("딜릿ㅌ!내용입눼다");
		comment.setWriter("기여운채형입니닷");
			
		service.register(comment);
		
		boolean res = service.remove(comment.getCno());
		
		assertTrue(res);
	}
	
	@Test
	public void testUpdate() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("업데잇ㅌ!내용입눼다");
		comment.setWriter("기여운채형입니닷");
		
		service.register(comment);
		
		
		comment.setContent("변경 후 내용");
		
	
		assertTrue(service.modify(comment));
		
		CommentVO up2 = service.get(comment.getCno());

		assertEquals("변경 후 내용", up2.getContent());
	}	
	
}
