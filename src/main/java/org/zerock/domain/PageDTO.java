package org.zerock.domain;
//handler와 jsp 사이에서 

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private int total; //총 페이지
	
	private Criteria cri;
	
	private boolean prev;
	private boolean next;

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int) Math.ceil(cri.getPageNum() / 10.0) * 10;
		this.startPage = endPage - 9;
		
		int realEnd = (int) Math.ceil(total * 1.0 / cri.getAmount());
		endPage = Math.min(realEnd, endPage);
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEnd;
		
	}
}
