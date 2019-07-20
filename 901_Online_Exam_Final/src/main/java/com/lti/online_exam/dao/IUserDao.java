package com.lti.online_exam.dao;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Login;
import com.lti.online_exam.model.User;

public interface IUserDao {
	public User addUser(User user)throws ExamException;
	public boolean authenticateUser(Login login)throws ExamException;
}
