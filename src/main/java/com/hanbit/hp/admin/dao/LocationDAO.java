package com.hanbit.hp.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.hp.util.KeyUtils;
@Repository
public class LocationDAO {
	@Autowired
	private SqlSession sqlSession;
	public List selectList() {
		return sqlSession.selectList("admin.location.selectList");
	}
	public Map selectOne(String locationId) {
		return sqlSession.selectOne("admin.location.selectOne",locationId);
	}
	public int update(String locationId, String locationName) {
		Map pram = new HashMap();
		pram.put("locationId",locationId);
		pram.put("locationName",locationName);
		return sqlSession.update("admin.location.update",pram);
	}
	public int delete(String locationId) {
		return sqlSession.delete("admin.location.delete",locationId);
	}
	public int insert(String locationId, String locationName) {
		Map pram = new HashMap();
		pram.put("locationId",locationId);
		pram.put("locationName",locationName);
		return sqlSession.insert("admin.location.insert",pram);
	}

}
