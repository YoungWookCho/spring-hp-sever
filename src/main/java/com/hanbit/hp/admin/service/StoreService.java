package com.hanbit.hp.admin.service;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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
	/* 이미지가 안들어갔을 경우에 롤백 하기 위해서 트랜잭션을 걸어준다*/
	@Transactional
	public int add(String storeName, String categoryId, 
			String locationId, MultipartFile storeImgFile) {
		
		String storeId = KeyUtils.generateKey("STO");
		String storeImg = "/api2/file/"+storeId;
		int result =storeDAO.insert(storeId, storeName, storeImg, categoryId, locationId);
		
		/* File저장*/
		fileService.addAndSave(storeId, storeImgFile);

		return result;
	}

}
