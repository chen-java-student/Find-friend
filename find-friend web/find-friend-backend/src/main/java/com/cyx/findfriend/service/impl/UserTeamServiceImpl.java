package com.cyx.findfriend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyx.findfriend.model.domain.UserTeam;
import com.cyx.findfriend.service.UserTeamService;
import com.cyx.findfriend.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author cyx
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-03-02 21:59:41
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




