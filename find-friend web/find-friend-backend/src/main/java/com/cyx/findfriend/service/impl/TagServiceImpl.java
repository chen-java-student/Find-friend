package com.cyx.findfriend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyx.findfriend.model.domain.Tag;
import com.cyx.findfriend.mapper.TagMapper;
import com.cyx.findfriend.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author 86178
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-02-24 14:44:03
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




