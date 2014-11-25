/**
 * 
 */
package org.sagacity.sshbase.controller.manage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.sagacity.core.controller.BaseAction;
import org.sagacity.core.page.Pager;
import org.sagacity.sshbase.entity.Classroom;
import org.sagacity.sshbase.entity.Student;
import org.sagacity.sshbase.service.ClassroomService;
import org.sagacity.sshbase.service.StudentService;

/**
 * @author Admin
 * 
 */
@Results( {
		@Result(name = "toList", type = "redirect", location = "/manage/student!list.action"),
		@Result(name = "json", type = "json", params = { "root", "student" }) })
public class StudentAction extends BaseAction {
	private static final long serialVersionUID = -5282194937393162114L;
	@Resource
	private StudentService studentService;
	@Resource
	private ClassroomService classroomService;

	private List<Student> list;
	private Student student;
	private List<Classroom> classroomList;
	private Pager<Student> pager;
	private Integer currentPage;

	/**
	 * 列表页
	 * 
	 * @return
	 */
	public String list() {
		pager = studentService.queryPage(currentPage, 5, studentService
				.getCriteria().addOrder(Order.desc("id")));
		return "list";
	}

	/**
	 * 添加页
	 * 
	 * @return
	 */
	public String toAdd() {
		classroomList = classroomService.queryByCriteria(classroomService
				.getCriteria().addOrder(Order.desc("id")));
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		studentService.save(student);
		return "toList";
	}

	/**
	 * 修改页
	 * 
	 * @return
	 */
	public String toEdit() {
		if (null != student && null != student.getId()) {
			classroomList = classroomService.queryByCriteria(classroomService
					.getCriteria().addOrder(Order.desc("id")));
			student = studentService.fetch(student.getId());
		}
		return "edit";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String edit() {
		studentService.update(student);
		return "toList";
	}

	public String json() {
		student = studentService.fetch(1);
		return "json";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		studentService.delete(student);
		return "toList";
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Classroom> getClassroomList() {
		return classroomList;
	}

	public void setClassroomList(List<Classroom> classroomList) {
		this.classroomList = classroomList;
	}

	public Pager<Student> getPager() {
		return pager;
	}

	public void setPager(Pager<Student> pager) {
		this.pager = pager;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
