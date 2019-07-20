package com.lti.online_exam.dao;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Document;

public interface IFileStorageDao {
	public Document addQuestionDoc(Document doc) throws ExamException;
}
