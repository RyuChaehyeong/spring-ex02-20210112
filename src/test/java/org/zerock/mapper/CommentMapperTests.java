package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CommentVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper mapper;
	
	
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testGetList() {
		List<CommentVO> list = mapper.getList();
		assertNotEquals(list.size(), 0);
	}
	
	@Test
	public void testInsert() {
		
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("newComment");
		comment.setWriter("newboe");
		
		int before = mapper.getList().size();
		mapper.insert(comment);
		int after = mapper.getList().size();
		
		assertEquals(before+1, after);
	}
	
	
	@Test
	public void testInsertSelectKey() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("newComment새로들어감");
		comment.setWriter("new글쓰니");
		
		int before = mapper.getList().size();
		mapper.insert(comment);
		int after = mapper.getList().size();
		assertEquals(before+1, after);
	
	}
	
	

	@Test
	public void testRead() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("댓글입니다");
		comment.setWriter("댓쓴입니다");
		
		mapper.insertSelectKey(comment);
		
		CommentVO readComment = mapper.read(comment.getCno());
		assertNotNull(readComment);
		assertEquals(readComment.getCno(), comment.getCno());

		log.info(readComment.getCno() + "야아ㅏ아아아아아");
		log.info(readComment.getCno());
		log.info(comment.getCno());
		
	}
	
	@Test
	public void testDelete() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("댓글입니다");
		comment.setWriter("댓쓴입니다");
		
		mapper.insertSelectKey(comment);
		
		int before = mapper.getList().size();
		
		int deleteNum = mapper.delete(comment.getCno());
		assertEquals(deleteNum, 1);
		
		int after = mapper.getList().size();
		assertEquals(before-1, after);
	}
	
	@Test
	public void testUpate() {
		CommentVO comment = new CommentVO();
		comment.setBno(1L);
		comment.setContent("안녕하세요");
		comment.setWriter("채형입니다.");
		
		mapper.insertSelectKey(comment);
		
		comment.setContent("반가워어");
		
		int updateNum = mapper.update(comment);
		assertEquals(updateNum, 1);
		
		CommentVO updatedVO = mapper.read(comment.getCno());
		assertEquals("반가워어", updatedVO.getContent());
	}
}
