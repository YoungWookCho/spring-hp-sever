package com.hanbit.hp.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {

	
	private SqlSession sqlSession;
	public int insert (String fileId, String fileType, long fileSize, String fileName) {
		
		Map pram = new HashMap();
		pram.put("fileId", fileId);
		pram.put("fileType", fileType);
		pram.put("fileSize", fileSize);
		pram.put("fileName", fileName);
	
		sqlSession.insert("file.insert", pram);
		return 0;
	}
	public Map selectOne(String fileId) {
		return sqlSession.selectOne("file.selectOne", fileId);
	}

}