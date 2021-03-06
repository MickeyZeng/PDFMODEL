/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.web;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import com.ckfinder.connector.configuration.Configuration;
import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.utils.AccessControlUtil;

/**
 * CKFinder配置
 * @author ThinkGem
 * @version 2014-06-25
 */
public class CKFinderConfig extends Configuration {

	public CKFinderConfig(ServletConfig servletConfig) {
        super(servletConfig);  
    }
	
	@Override
    protected Configuration createConfigurationInstance() {
		Principal principal = (Principal) UserUtils.getPrincipal();
		String comName = UserUtils.getUser().getCompany().getName();
		String officeName = UserUtils.getUser().getOffice().getName();
		if (principal == null){
			return new CKFinderConfig(this.servletConf);
		}
		boolean isView = true;//UserUtils.getSubject().isPermitted("cms:ckfinder:view");
		boolean isUpload = true;//UserUtils.getSubject().isPermitted("cms:ckfinder:upload");
		boolean isEdit = true;//UserUtils.getSubject().isPermitted("cms:ckfinder:edit");
		AccessControlLevel alc = this.getAccessConrolLevels().get(0);
		alc.setFolderView(isView);
		alc.setFolderCreate(isEdit);
		alc.setFolderRename(isEdit);
		alc.setFolderDelete(isEdit);
		alc.setFileView(isView);
		alc.setFileUpload(isUpload);
		alc.setFileRename(isEdit);
		alc.setFileDelete(isEdit);
//		for (AccessControlLevel a : this.getAccessConrolLevels()){
//			System.out.println(a.getRole()+", "+a.getResourceType()+", "+a.getFolder()
//					+", "+a.isFolderView()+", "+a.isFolderCreate()+", "+a.isFolderRename()+", "+a.isFolderDelete()
//					+", "+a.isFileView()+", "+a.isFileUpload()+", "+a.isFileRename()+", "+a.isFileDelete());
//		}
		AccessControlUtil.getInstance(this).loadACLConfig();
		try {
//			Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
//			this.baseURL = ServletContextFactory.getServletContext().getContextPath()+"/userfiles/"+principal+"/";
//			this.baseURL = FileUtils.path("/Users/mickey/document/PDFModel/CKFile/" + principal + "/");
//			this.baseDir = FileUtils.path("/Users/mickey/document/PDFModel/CKFile/" + principal + "/");
			if(UserUtils.getUser().getUserType().equals("1")){
				this.baseDir=FileUtils.path("/Users/mickey/document/PDFModel/CKFile/1/");
				this.baseURL=FileUtils.path("/Users/mickey/document/PDFModel/CKFile/1/");
			}else if(UserUtils.getUser().getUserType().equals("2")){
				this.baseDir=FileUtils.path("/Users/mickey/document/PDFModel/CKFile/1/Group/"+comName+"/");
				this.baseURL=FileUtils.path("/Users/mickey/document/PDFModel/CKFile/1/Group/"+comName+"/");
			}else if(UserUtils.getUser().getUserType().equals("3")){
				this.baseDir=FileUtils.path("/Users/mickey/document/PDFModel/CKFile/1/Group/"+comName+"/Group/"+officeName+"/");
				this.baseURL=FileUtils.path("/Users/mickey/document/PDFModel/CKFile/1/Group/"+comName+"/Group/"+officeName+"/");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new CKFinderConfig(this.servletConf);
    }

    @Override  
    public boolean checkAuthentication(final HttpServletRequest request) {
        return UserUtils.getPrincipal()!=null;
    }

}
