package com.thinkgem.jeesite.modules.uploadPDF.Service;


import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.modelement.dao.SysModelementDao;
import com.thinkgem.jeesite.modules.modelement.entity.SysModelement;
import com.thinkgem.jeesite.modules.sysmod.dao.ModTimesDao;
import com.thinkgem.jeesite.modules.sysmod.entity.ModTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UploadService extends BaseService {

    @Autowired
    SysModelementDao sysModelementDao;

    @Autowired
    ModTimesDao modTimesDao;

    //通过公司的ID获取该公司的特殊元素
    public List<SysModelement> findElementByComId(SysModelement sysModelement) {
        List<SysModelement> list = sysModelementDao.findListByUserType(sysModelement);
        return list;
    }


    public int checkUse(ModTimes modTimes) {
        int times = 0;
        List<ModTimes> list = modTimesDao.findList(modTimes);
        for(int i = 0; i < list.size() ; i++){
            times = Integer.parseInt(list.get(i).getTimes());
        }

        if(times > 0){
            return 1 ;
        }

        return 0;
    }

    @Transactional(readOnly = false)
    public void reduce(ModTimes modTimes) {
        int times = 0;
        List<ModTimes> list = modTimesDao.findList(modTimes);
        for(int i = 0; i < list.size() ; i++){
            times = Integer.parseInt(list.get(i).getTimes());
            modTimes = list.get(i);
        }
        times = times - 1 ;
        modTimes.setTimes(String.valueOf(times));
        modTimesDao.update(modTimes);
    }
}
