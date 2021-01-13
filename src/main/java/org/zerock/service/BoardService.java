package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardService {
	public void register(BoardVO board);
	
	//xml파일에 어떤 클래스가 injection된다고 명시를 해주고
	//controller에는 service를 interface로 선언을 한다.
	//그 interface를 구현한 class이기만 하면 controller의 변경없이 
	//service class를 변경할 수 있다.
	
	public List<BoardVO> getList();
	
	public BoardVO get(Long bno);
	
	public boolean remove(Long bno);
	
	public boolean modify(BoardVO board);
	
}
