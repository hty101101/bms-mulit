/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.modules.sys.service;

import cn.amigosoft.modules.security.user.UserDetail;
import cn.amigosoft.modules.sys.dto.SysMenuDTO;
import cn.amigosoft.modules.sys.entity.SysMenuEntity;
import cn.amigosoft.common.service.BaseService;

import java.util.List;


/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

    SysMenuDTO get(Long id);

    void save(SysMenuDTO dto);

    void update(SysMenuDTO dto);

    void delete(Long id);

    /**
     * 菜单列表
     *
     * @param type 菜单类型
     */
    List<SysMenuDTO> getAllMenuList(Integer type);

    /**
     * 用户菜单列表
     *
     * @param user  用户
     * @param type 菜单类型
     */
    List<SysMenuDTO> getUserMenuList(UserDetail user, Integer type);

    /**
     * 根据父菜单，查询子菜单
     * @param pid  父菜单ID
     */
    List<SysMenuDTO> getListPid(Long pid);

}
