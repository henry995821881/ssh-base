/**
 * 
 */
package org.sagacity.sshbase.service;

import org.sagacity.core.service.BaseHibernateService;
import org.sagacity.sshbase.dao.StudentDao;
import org.sagacity.sshbase.entity.Student;

/**
 * @author Admin
 *
 */
public interface StudentService extends BaseHibernateService<Student, StudentDao> {

}
