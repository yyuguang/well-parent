package com.lnzz.ucenterservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.ucenterservice.pojo.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.ucenterservice.pojo.vo.LoginInfoVo;
import com.lnzz.ucenterservice.pojo.vo.LoginVo;
import com.lnzz.ucenterservice.pojo.vo.QueryVo;
import com.lnzz.ucenterservice.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-21
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 用户登录
     *
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

    /**
     * 用户注册
     *
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据token获取登录信息
     *
     * @param memberId
     * @return
     */
    LoginInfoVo getLoginInfo(String memberId);

    /**
     * 分页模糊查询
     *
     * @param memberPage
     * @param queryVo
     */
    void pageKeys(Page<UcenterMember> memberPage, QueryVo queryVo);

    /**
     * 禁用会员
     *
     * @param id
     * @return
     */
    void disableMemberById(String id);

    /**
     * 查询某一天注册人数
     *
     * @param day
     * @return
     */
    Integer countRegisterDay(String day);
}
