package com.yingchun.tsys.assignment.service;

import org.springframework.stereotype.Service;

@Service
public class SystemServiceA implements ServiceAdapter {

	@Override
	public void process(int month) {
		System.out.println("calling in service A");
	}

}
