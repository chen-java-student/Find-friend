package com.cyx.findfriend.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户加入队伍请求体
 *
 * @author cyx
 */
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;
    /**
     * id
     */
    private Long teamId;


    /**
     * 密码
     */
    private String password;
}