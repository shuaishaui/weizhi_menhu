package com.wizz.demo.service;

import com.wizz.demo.model.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface SysFileService {
    /**
     * 保存文件
     * @param file
     * @param param
     */


    /**
     * 获取所有数据
     * @return
     */
    List<SysFile> getList(SysFile sysFile);

    /**
     * 查询单个
     * @param id
     * @return
     */
    SysFile getById(int id);

    /**
     * 修改
     * @param sysFile
     */
    void update(MultipartFile file,Map param,int id);
    void update1(Map param,int id);

    /**
     * 根据id删除
     * @param id
     */
    void delete(int id);

}
