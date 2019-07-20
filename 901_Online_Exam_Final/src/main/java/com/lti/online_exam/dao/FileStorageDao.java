package com.lti.online_exam.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Document;

@Repository
public class FileStorageDao implements IFileStorageDao {

	@PersistenceContext
	private EntityManager enitityManager;

	@Override
	public  Document addQuestionDoc(Document doc) throws ExamException {
		enitityManager.persist(doc);
		return doc;
	}

}
