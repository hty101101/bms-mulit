/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.sys.entity.SysLanguageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 国际化
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysLanguageDao extends BaseDao<SysLanguageEntity> {

    SysLanguageEntity getLanguage(SysLanguageEntity entity);

    void updateLanguage(SysLanguageEntity entity);

    /**
     * 删除国际化
     * @param tableName   表名
     * @param tableId     表主键
     */
    void deleteLanguage(@Param("tableName") String tableName, @Param("tableId") Long tableId);

}