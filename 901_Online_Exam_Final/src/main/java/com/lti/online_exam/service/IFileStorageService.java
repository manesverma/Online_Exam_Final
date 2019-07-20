package com.lti.online_exam.service;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Document;

public interface IFileStorageService {

	public Document addQuestionDoc(Document doc) throws ExamException;

}
