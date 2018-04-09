package com.thinkgem.jeesite.modules.uploadPDF.Service;


import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.modelement.dao.SysModelementDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UploadService extends BaseService {

    @Autowired
    SysModelementDao sysModelementDao;

    //通过公司的ID获取该公司的特殊元素
    public void findElementByComId() {

    }
}
