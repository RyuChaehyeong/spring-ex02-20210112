package org.zerock.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.BoardMapperTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ =@Autowired)
	private BoardService service;
	
	@Setter(onMethod_ =@Autowired)
	private BoardMapper mapper;
	
	//bean으로 잘 만들어져서 injection 되었는지 확인
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("쩨목입니다");
		board.setContent("내용입눼다");
		board.setWriter("채형입니닷");
		
		int before = mapper.getList().size();
		service.register(board);
		int after = mapper.getList().size();
		
		assertEquals(before + 1, after);

	}
	
	@Test
	public void testGetList() {
		//List<BoardVO> list = service.getList();
		
		Criteria cri = new Criteria(2, 10);
		List<BoardVO> list = service.getList(cri);
		
		assertNotNull(list);
		assertNotEquals(list.size(), 0);
		assertEquals(list.size(), 10);
	}
	
	@Test
	public void testGet() {
		BoardVO board = new BoardVO();
		board.setTitle("쩨목입니다");
		board.setContent("내용입눼다");
		board.setWriter("채형입니닷");
			
		service.register(board);

		BoardVO vo = service.get(board.getBno());
		assertNotNull(vo);
		assertEquals(vo.getBno(), board.getBno());
	}
	@Test
	public void testDelete() {
		BoardVO board = new BoardVO();
		board.setTitle("딜릿ㅌ! 제목입니다");
		board.setContent("딜릿ㅌ!내용입눼다");
		board.setWriter("기여운채형입니닷");
			
		service.register(board);
		
		boolean res = service.remove(board.getBno());
		
		assertTrue(res);
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("제목입니다");
		board.setContent("내용입눼다");
		board.setWriter("글쓴이");
		
		service.register(board);
		
		board.setTitle("변경후 제목");
		board.setContent("변경 후 내용");
		
	
		assertTrue(service.modify(board));
		
		BoardVO up2 = service.get(board.getBno());
		
		assertEquals("변경후 제목", up2.getTitle());
		assertEquals("변경 후 내용", up2.getContent());
		
	}
}
