package com.cyx.findfriend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用的删除请求
 */
@Data
public class DeleteRequest implements Serializable {

    private static final long serialVersionUID = 5464292949537285900L;

    private long id;
}
