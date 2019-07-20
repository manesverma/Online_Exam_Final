package com.lti.online_exam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.online_exam.dao.IFileStorageDao;
import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Document;

@Service
public class FileStorageService implements IFileStorageService {

	@Autowired
	private IFileStorageDao fileStorageDao;
	
	@Override
	@Transactional
	public Document addQuestionDoc(Document doc) throws ExamException{
	
		return fileStorageDao.addQuestionDoc(doc);
	}

}
