package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class SampleTxService {
	
	private Sample1Mapper mapper1;
	
	private Sample2Mapper mapper2;
	
	@Transactional
	public void addData(String value) {
		mapper1.insertCol1(value);
		mapper2.insertCol2(value);
	}
}
