package com.haier.controller;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.haier.enums.StatusCodeEnum;
import com.haier.exception.HryException;
import com.haier.po.Tcase;
import com.haier.po.TcaseCustom;
import com.haier.response.Result;
import com.haier.service.TcaseService;
import com.haier.util.ReflectUtil;
import com.haier.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2018/5/30 15:05
 */
@Slf4j
@RestController
@RequestMapping("/tcase")
public class TcaseController {

    @Autowired
    TcaseService tcaseService;


    //增
    @PostMapping("/insertOne")
    public Result insertOne(Tcase tcase) {
        ReflectUtil.setInvalidFieldToNull(tcase, false);
        if (tcase == null || tcase.getIid() == null || tcase.getCasename() == null
                || tcase.getServiceid() == null) {
            throw new HryException(StatusCodeEnum.PARAMETER_ERROR, "添加case时,serviceid,iid,casename必填!");
        }
        return ResultUtil.success(tcaseService.insertOne(tcase));
    }

    /**
     * 删除单个tcase记录
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteOne")
    public Result deleteOne(@RequestParam("id") Integer id) {
        return ResultUtil.success(tcaseService.deleteOne(id));
    }

    /**
     * 根据条件删除tcase记录,现在仅支持根据iId和envId删除tcase记录
     *
     * @param tcase
     * @return
     */
    @PostMapping("/deleteByCondition")
    public Result deleteByCondition(Tcase tcase) {
        return ResultUtil.success(tcaseService.deleteByCondition(tcase));
    }

    //改
    @PostMapping("/updateOne")
    public Result updateOne(Tcase tcase) {
        ReflectUtil.setInvalidFieldToNull(tcase, false);
        if (tcase == null || tcase.getId() == null) {
            throw new HryException(StatusCodeEnum.PARAMETER_ERROR, "更新case时,id必填!");
        }
        return ResultUtil.success(tcaseService.updateOne(tcase));
    }

    //查-综合查询
    @PostMapping("/selectByCondition")
    public Result selectByCondition(TcaseCustom tcaseCustom, Integer pageNum, Integer pageSize) {
        return ResultUtil.success(tcaseService.selectByContion(tcaseCustom, pageNum, pageSize));
    }

    //查-返回List-带Result
    @PostMapping("/selectByConditionSimple")
    public Result selectListByCondition(Tcase tcase) {
        return ResultUtil.success(tcaseService.selectByCondition(tcase));
    }

    //查-仅返回List
    @PostMapping("/selectList")
    public List<Tcase> selectList(Tcase tcase) {
        return tcaseService.selectByCondition(tcase);
    }


    //查-主键查询
    @PostMapping("/selectOne")
    public Result selectOne(@RequestParam("id") Integer id) {
        return ResultUtil.success(tcaseService.selectOne(id));
    }

    /**
     * 运行单条case,如果不指定运行环境,系统将从服务环境映射表中寻找相应环境
     * 适用于新建case页面和编辑case页面调用此接口测试case
     */
    @PostMapping("/runCaseOne")
    public Result runCaseOne(Tcase tcase) throws HttpProcessException {

        if (tcase == null || tcase.getIid() == null) {
            throw new HryException(StatusCodeEnum.PARAMETER_ERROR, "接口id必填!");
        }
        return ResultUtil.success(tcaseService.runOne(tcase));
    }

    /**
     * 运行单条case,传入caseid,适用于case列表页运行时调用此接口
     */
    @PostMapping("/runCaseOneById")
    public Result runCaseOneById(@RequestParam("id") Integer id) throws HttpProcessException {
        return ResultUtil.success(tcaseService.runOne(id));
    }

}
