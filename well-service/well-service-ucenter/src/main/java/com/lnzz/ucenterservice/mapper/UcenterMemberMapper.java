package com.lnzz.ucenterservice.mapper;

import com.lnzz.ucenterservice.pojo.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-21
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    /**
     * 根据用户id禁用会员     *
     *
     * @param id
     */
    void disableMemberById(@Param("id") String id);

}
