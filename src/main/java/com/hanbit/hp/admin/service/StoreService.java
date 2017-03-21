package com.hanbit.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.hp.admin.dao.StoreDAO;
import com.hanbit.hp.service.FileService;
import com.hanbit.hp.util.KeyUtils;

@Service
public class StoreService {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private StoreDAO storeDAO;
	
	public List getList(int page, int rowsPerPage) {
		return storeDAO.selectList(page, rowsPerPage);
	}
	
	public int count() {
		return storeDAO.count();
	}
	
	public Map get(String storeId) {
		return storeDAO.selectOne(storeId);
	}
	
	@Transactional
	public int modify(String storeId, String storeName,
			String categoryId, String locationId, Map storeDetail,
			MultipartFile storeImgFile) {
		
		int result = storeDAO.update(storeId, storeName, categoryId, locationId);
		
		storeDAO.updateDetail(storeDetail);
		
		if (storeImgFile != null) {
			fileService.updateAndSave(storeId, storeImgFile);
		}
		
		return result;
	}
	
	@Transactional
	public int remove(String storeId) {
		storeDAO.deleteDetail(storeId);
		
		int result = storeDAO.delete(storeId);
		
		fileService.delete(storeId);
		
		return result;
	}

	@Transactional
	public int add(String storeName,
			String categoryId, String locationId, Map storeDetail,
			MultipartFile storeImgFile) {
		
		String storeId = KeyUtils.generateKey("STO");
		String storeImg = "/api2/file/" + storeId;	
		
		int result = storeDAO.insert(storeId, storeName, storeImg, categoryId, locationId);
		
		storeDetail.put("storeId", storeId);
		
		storeDAO.insertDetail(storeDetail);
		
		fileService.addAndSave(storeId, storeImgFile);
		
		return result;
	}
	
}








