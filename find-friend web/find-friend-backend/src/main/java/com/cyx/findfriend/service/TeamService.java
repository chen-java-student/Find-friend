package com.cyx.findfriend.service;

import com.cyx.findfriend.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyx.findfriend.model.domain.User;
import com.cyx.findfriend.model.dto.TeamQuery;
import com.cyx.findfriend.model.request.TeamJoinRequest;
import com.cyx.findfriend.model.request.TeamQuitRequest;
import com.cyx.findfriend.model.request.TeamUpdateRequest;
import com.cyx.findfriend.model.vo.TeamUserVO;

import java.util.List;

/**
* @author cyx
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-03-02 21:58:02
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery,boolean isAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest,User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest,User loginUser);

    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param loginUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 解散队伍
     * @param id
     * @return
     */
    boolean deleteTeam(long id,User loginUser);

    List<User> listJoinUsers(Long id);
}
