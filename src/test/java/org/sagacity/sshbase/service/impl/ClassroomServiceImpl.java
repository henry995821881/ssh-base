/**
 * 
 */
package org.sagacity.sshbase.service.impl;

import javax.annotation.Resource;

import org.sagacity.core.service.impl.BaseHibernateServiceImpl;
import org.sagacity.sshbase.dao.ClassroomDao;
import org.sagacity.sshbase.entity.Classroom;
import org.sagacity.sshbase.service.ClassroomService;
import org.springframework.stereotype.Service;

/**
 * @author Admin
 *
 */
@Service
public class ClassroomServiceImpl extends BaseHibernateServiceImpl<Classroom, ClassroomDao>
		implements ClassroomService {
	@Resource
	private ClassroomDao dao;

	@Override
	public ClassroomDao getDaoImpl() {
		return dao;
	}

}
