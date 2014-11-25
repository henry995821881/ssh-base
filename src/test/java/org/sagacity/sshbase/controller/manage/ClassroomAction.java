/**
 * 
 */
package org.sagacity.sshbase.controller.manage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.sagacity.core.controller.BaseAction;
import org.sagacity.sshbase.entity.Classroom;
import org.sagacity.sshbase.service.ClassroomService;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * @author LIZHITAO 测试action
 * 
 */
@Result(name = "toList",type = "redirect", location = "/manage/classroom!list.action")
public class ClassroomAction extends BaseAction {
	private static final long serialVersionUID = -454569768881669716L;
	@Resource
	private ClassroomService classroomService;

	private List<Classroom> list;
	private Classroom classroom;

	/**
	 * 列表页
	 * @return
	 */
	@SkipValidation
	public String list() {
		list = classroomService.findAll();
		return "list";
	}

	/**
	 * 添加页
	 * @return
	 */
	@SkipValidation
	public String toAdd() {
		return INPUT;
	}

	/**
	 * 添加
	 * @return
	 */
	@Validations(requiredStrings = { @RequiredStringValidator(type = ValidatorType.SIMPLE, trim = true, fieldName = "classroom.name", message = "请输入班级名称") })
	public String add() {
		classroomService.save(classroom);
		return "toList";
	}

	public List<Classroom> getList() {
		return list;
	}

	public void setList(List<Classroom> list) {
		this.list = list;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

}
