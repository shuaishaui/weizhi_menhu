package com.wizz.demo.service.Impl;

import com.wizz.demo.dao.SysFileDao;
import com.wizz.demo.model.SysFile;
import com.wizz.demo.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SysFileServiceImpl implements SysFileService {
    @Autowired
    private SysFileDao sysFiledao;


    private String dirPath = "D:/files-data";



    @Override
    public List<SysFile> getList(SysFile sysFile) {
        return sysFiledao.getList(sysFile);
    }

    @Override
    public SysFile getById(int id) {
        return sysFiledao.get(id);
    }

    @Override
    public void update(MultipartFile file,Map param,int id) {
        //将上传的文件保存至本地服务器 返回最终路径
        String desPath = saveFileToServer(file);
        //保存相关资料数据
        SysFile sysFile = new SysFile();
        sysFile.setContent(param.get("content").toString());
        sysFile.setFileName(file.getOriginalFilename());
        sysFile.setFilePath(desPath);

        sysFile.setId(id);

        sysFile.setTheme(param.get("theme").toString());
        sysFiledao.update(sysFile);
    }

    @Override
    public void delete(int id) {
        sysFiledao.del(id);
    }

    /*************************************************************************************/
    /**
     * 将文件保存至服务器路径
     *
     * @param file
     */
    private String saveFileToServer(MultipartFile file) {
        String path = "";
        String fileName = file.getOriginalFilename();
        //文件存放路径判断
        File destFile = new File(dirPath);
        if (destFile.exists()) {//如果存在
            //判断文件是否存在并保存
            isExist(file, destFile, fileName);
        } else {
            //不存在就创建文件夹
            destFile.mkdir();
            //判断文件是否存在并保存
            isExist(file, destFile, fileName);
        }
        return dirPath + "/" + fileName;
    }


    /**
     * 判断文件是否存在 并且保存
     *
     * @param file     需要保存的文件
     * @param destFile 保存路径
     * @param fileName 文件名
     */
    private void isExist(MultipartFile file, File destFile, String fileName) {
        try {
            Map<String, File> files = new HashMap<>();
            String[] fileList = destFile.list();
            if (fileList.length == 0) {
                file.transferTo(new File(destFile + "/" + fileName));
            } else {
                //判断是否有重复文件
                for (String name : fileList) {
                    File f = new File(destFile.getAbsoluteFile() + "/" + name);
                    if (!f.isDirectory() && !name.equals(fileName)) {
                        files.put(f.getName(), f);
                    }
                }
                //原文件夹里不存在直接将文件保存
                if (files.get(fileName) == null || files.size() == 0) {
                    file.transferTo(new File(destFile + "/" + fileName));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
