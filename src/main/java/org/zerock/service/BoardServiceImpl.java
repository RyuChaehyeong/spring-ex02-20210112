package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Component
@Service
@AllArgsConstructor //모든 필드로 생성자 만듦
@Log4j
public class BoardServiceImpl implements BoardService {
	//실제 관리되는 Bean은 이거임 BoardService가 아니라

	private BoardMapper mapper;
	/*
	//dependency injection by using constructor
	@Autowired
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}
	@AllArgsConstructor로 대신함. 생성자 하나니까 자동으로 dependency injection
	*/
	
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	}
	
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
				
	}

	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board) == 1;
	}
	
	


}
