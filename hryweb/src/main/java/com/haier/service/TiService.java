package com.haier.service;

import com.github.pagehelper.PageInfo;
import com.haier.po.Ti;
import com.haier.po.TiCustom;

import java.util.List;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2018/5/9 16:09
 */
public interface TiService {
    /**
     *@description: 插入一条用例接口
     *@params: Ti
     *@return: 返回插入的ID
     *@author: luqiwei
     *@date: 2018-05-18
     */
    Integer insertOne(Ti ti);
    Integer updateOne(Integer id,Ti ti);
    Ti selectOne(Integer id);
    PageInfo<TiCustom> selectByCondition(TiCustom tiCustom, Integer pageNum, Integer pageSize);
}
