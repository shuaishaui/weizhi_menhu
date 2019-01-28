package com.wizz.demo.Controller;

import com.wizz.demo.model.Introduce;
import com.wizz.demo.service.IntroduceService;
import com.wizz.demo.util.ResultData;
import com.wizz.demo.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/introduce")
public class IntroduceController {


    @Autowired
    private IntroduceService introduceService;


    /**
     * 获取所有数据
     *
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public
    List<Introduce> getList(Introduce introduce) {
        return introduceService.getList(introduce);
    }

    /**
     * 根据id获取单个数据
     *
     * @return
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public
    Introduce get(@PathVariable("id") int id) {
        return introduceService.getById(id);
    }

    /**
     * 根据id删除
     *
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public
    ResultData delete(@PathVariable("id") int id) {
        introduceService.delete(id);
        ResultData res = new ResultData();
        res.setMsg("删除成功!");
        return res;
    }


    /**
     * 修改
     *
     * @param file
     * @param param
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public
    ResultData update(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> param, @PathVariable("id") int id) {
        Object t = param.get("theme");
        if (t == null || t.toString().equals("")) {
            return ResultUtils.error("模块主题不能为空！");
        }
        String theme = t.toString();
        if (theme.toCharArray().length > 20) {
            return ResultUtils.error("主题不能超过10个汉字!");
        }
        Object content = param.get("content");
        if (content == null || content.toString().equals("")) {
            return ResultUtils.error("模块简介不能为空！");
        }
        String c = content.toString();
        if (c.toCharArray().length > 150) {
            return ResultUtils.error("简介不能超过75个汉字!");
        }
//        try {
//            introduceService.update(file,param,id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ResultData res = new ResultData();
//        res.setMsg("修改成功！");
//        return res;
//    }
//
//}
        if (file == null) {
            try {
                introduceService.update1(param, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResultData res = new ResultData();
            res.setMsg("修改成功！");
            return res;
        } else {
            try {
                introduceService.update(file, param, id);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ResultData res = new ResultData();
            res.setMsg("修改成功！");
            return res;
        }


    }
}
