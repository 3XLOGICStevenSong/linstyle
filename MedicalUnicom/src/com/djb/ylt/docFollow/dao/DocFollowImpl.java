package com.djb.ylt.docFollow.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.docFollow.entity.DocFollowEntity;
import com.djb.ylt.framework.dao.BaseDAOImpl;

@Repository("docFollowDao")
public class DocFollowImpl  extends BaseDAOImpl<DocFollowEntity> implements 
IDocFollowDao  {

}
