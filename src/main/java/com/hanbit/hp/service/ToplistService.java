package com.hanbit.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.hp.dao.ToplistDAO;

@Service
public class ToplistService {
	@Autowired
	private ToplistDAO topListDAO;
	
	public List<Map<String, Object>> getAll(){
		return topListDAO.selectAll();
	}
	

}
