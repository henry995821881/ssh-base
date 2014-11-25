/**
 * 
 */
package org.sagacity.sshbase.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.sagacity.core.controller.BaseAction;

/**
 * @author 文件上传类
 * 
 */
@Result(name = "fileJson", type = "json", params = { "root", "filePaths" })
@InterceptorRefs(value = {@InterceptorRef("fileUploadStack")})
public class FileuploadAction extends BaseAction {
	private static final long serialVersionUID = -6574229302523373869L;
	private File[] upload;
	// 封装上传文件类型的属性
	private String[] uploadContentType;
	// 封装上传文件名的属性
	private String[] uploadFileName;
	private List<String> filePaths = new LinkedList<String>();

	/**
	 * 上传单个文件
	 * 
	 * @return
	 */
	public String execute() {

		String filePath = "";// 保存上传文件的路径
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			/** 构建图片保存的目录 **/
			String accessPath = "/upload/" + dateformat.format(new Date())
					+ "/";
			/** 得到图片保存目录的真实路径 **/
			String localPathDir = getRequest().getSession().getServletContext()
					.getRealPath(accessPath);
			/** 根据真实路径创建目录 **/
			File logoSaveFile = new File(localPathDir);
			if (!logoSaveFile.exists()) {
				logoSaveFile.mkdirs();
			}

			String suffix = "";
			if (null != upload) {
				for (int i = 0; i < upload.length; i++) {
					if (-1 != getUploadFileName()[i].lastIndexOf(".")) {
						suffix = getUploadFileName()[i]
								.substring(getUploadFileName()[i]
										.lastIndexOf("."));
					}
					/** 使用UUID生成文件名称 **/
					String localFileName = UUID.randomUUID().toString()
							+ suffix;
					/** 拼成完整的文件保存路径加文件 **/
					String fileName = localPathDir + File.separator
							+ localFileName;
					File destfile = new File(fileName);
					filePath = accessPath + localFileName;

					try {
						FileUtils.copyFile(upload[i], destfile);
						filePaths.add(filePath);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fileJson";
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}

}
