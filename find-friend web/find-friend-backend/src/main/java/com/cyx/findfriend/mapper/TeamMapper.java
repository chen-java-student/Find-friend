package com.cyx.findfriend.mapper;

import com.cyx.findfriend.model.domain.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyx.findfriend.model.domain.User;

import java.util.List;

/**
* @author cyx
* @description 针对表【team(队伍)】的数据库操作Mapper
* @createDate 2024-03-02 21:58:02
*/
public interface TeamMapper extends BaseMapper<Team> {

    /**
     * 根据队伍id 查询加入队伍的用户信息列表
     *
     * @param id
     * @return
     */
    List<User> selectJoinUsers(Long id);
}




