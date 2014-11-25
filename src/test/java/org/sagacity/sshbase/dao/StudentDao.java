/**
 * 
 */
package org.sagacity.sshbase.dao;

import org.sagacity.core.dao.impl.BaseHibernateDaoImpl;
import org.sagacity.sshbase.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 * 
 */
@Repository
public class StudentDao extends BaseHibernateDaoImpl<Student, Integer> {

}
