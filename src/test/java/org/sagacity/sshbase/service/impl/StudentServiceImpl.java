/**
 * 
 */
package org.sagacity.sshbase.service.impl;

import javax.annotation.Resource;

import org.sagacity.core.service.impl.BaseHibernateServiceImpl;
import org.sagacity.sshbase.dao.StudentDao;
import org.sagacity.sshbase.entity.Student;
import org.sagacity.sshbase.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author Admin
 * 
 */
@Service
public class StudentServiceImpl extends
		BaseHibernateServiceImpl<Student, StudentDao> implements StudentService {
	@Resource
	private StudentDao dao;

	@Override
	public StudentDao getDaoImpl() {
		return dao;
	}

}
