package com.project.system.service.impl;

import java.util.Date;
import java.util.List;

import com.project.common.utils.TimeUuidUtil;
import com.project.finals.RedisKeys;
import com.project.util.RedisUtil;
import com.project.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.constant.UserConstants;
import com.project.common.support.Convert;
import com.project.common.utils.StringUtils;
import com.project.system.domain.SysDictType;
import com.project.system.mapper.SysDictDataMapper;
import com.project.system.mapper.SysDictTypeMapper;
import com.project.system.service.ISysDictTypeService;

/**
 * 字典 业务层处理
 *
 * @author lws
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(String dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeById(String dictId) {
        return dictTypeMapper.deleteDictTypeById(dictId);
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String ids) throws Exception {
//        String[] dictIds = Convert.toLongArray(ids);
//        for (String dictId : dictIds) {
//            SysDictType dictType = selectDictTypeById(dictId);
//            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
//                throw new Exception(String.format("%1$s已分配,不能删除", dictType.getDictName()));
//            }
//        }

//        return dictTypeMapper.deleteDictTypeByIds(dictIds);
        return 0;
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType) {
        dictType.setDictId(UUIDUtil.getUUID());
        dictType.setStatus("0");
        dictType.setCreateTime(new Date());
        int row=dictTypeMapper.insertDictType(dictType);
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(SysDictType dictType) {
//        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
//        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row= dictTypeMapper.updateDictType(dictType);
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && !dictType.getDictId().equals(dict.getDictId())) {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }
}
