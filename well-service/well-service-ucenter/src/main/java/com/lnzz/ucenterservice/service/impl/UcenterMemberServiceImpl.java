package com.lnzz.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JwtUtils;
import com.lnzz.commonutils.Md5Util;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import com.lnzz.ucenterservice.pojo.UcenterMember;
import com.lnzz.ucenterservice.mapper.UcenterMemberMapper;
import com.lnzz.ucenterservice.pojo.vo.LoginInfoVo;
import com.lnzz.ucenterservice.pojo.vo.LoginVo;
import com.lnzz.ucenterservice.pojo.vo.QueryVo;
import com.lnzz.ucenterservice.pojo.vo.RegisterVo;
import com.lnzz.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-21
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(mobile)) {
            throw new WellParamException(20001, "error");
        }

        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (member == null) {
            throw new WellParamException(20001, "登录失败");
        }
        //校验密码
        if (!Md5Util.encrypt(password).equals(member.getPassword())) {
            throw new WellParamException(20001, "登录失败");
        }
        //校验是否被禁用
        if (member.getIsDisabled()) {
            throw new WellParamException(20001, "登录失败");
        }
        //使用JWT生成token字符串
        return JwtUtils.getJwtToken(member.getId(), member.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new WellParamException(20001, "注册失败");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobileCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(mobileCode)) {
            throw new WellParamException(20001, "注册失败");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (count.intValue() > 0) {
            throw new WellParamException(20001, "注册失败");
        }
        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(Md5Util.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://182.61.1.209/foodie/user_face.jpg");
        baseMapper.insert(member);
    }

    @Override
    public LoginInfoVo getLoginInfo(String memberId) {
        UcenterMember member = baseMapper.selectById(memberId);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(member, loginInfoVo);
        return loginInfoVo;
    }

    @Override
    public void pageKeys(Page<UcenterMember> memberPage, QueryVo queryVo) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (queryVo == null) {
            baseMapper.selectPage(memberPage, queryWrapper);
            return;
        }

        String mobile = queryVo.getMobile();
        String begin = queryVo.getBegin();
        String end = queryVo.getEnd();

        if (!StringUtils.isEmpty(mobile)) {
            queryWrapper.like("mobile", mobile);
        }
        if (!org.springframework.util.StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!org.springframework.util.StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        baseMapper.selectPage(memberPage, queryWrapper);
    }

    @Override
    public void disableMemberById(String id) {
        baseMapper.disableMemberById(id);
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
