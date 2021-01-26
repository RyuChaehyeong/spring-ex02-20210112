package org.zerock.mapper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {375L, 373L, 372L, 370L, 369L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			log.info(i+ "," + (i % 5));
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓테");
			vo.setReplyer("replyer" + i);
			mapper.insert(vo);
			
		});
	}
	
	
	@Test
	public void testCreate2() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(364L);
		vo.setReply("댓테스트");
		vo.setReplyer("user00");
		
		mapper.insert(vo);
		
		try {
			vo.setBno(374L); //tbl_board 테이블에 없는 값
			mapper.insert(vo);
			fail();
			
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testRead() {
		Long targetRno = 6L;
		ReplyVO vo = mapper.read(targetRno);
		
		assertEquals("replyer3", vo.getReplyer());
		
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 6L;
		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("댓댓 수정 댓댓");
		int cnt = mapper.update(vo);
		assertEquals(cnt, 1);
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		mapper.delete(targetRno);
	}
	
	
	@Test
	public void testList() {
		List<ReplyVO> list = mapper.getListWithPaging(null, 364L);
		log.info(list.size()+"**********************************");
		assertNotEquals(0, list.size());
	}
	
}






