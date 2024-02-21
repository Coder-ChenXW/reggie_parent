package com.reggie.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reggie.dto.UserLoginDTO;
import com.reggie.entity.User;
import com.reggie.mapper.UserMapper;
import com.reggie.service.UserService;
import com.reggie.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;
    @Value("${reggie.wechat.appid}")
    private String appid;
    @Value("${reggie.wechat.secret}")
    private String secret;

    /**
     * 根据微信授权码实现微信登录
     * @param userLoginDTO
     * @return
     */
    public User wxLogin(UserLoginDTO userLoginDTO) {
        //授权码
        String code = userLoginDTO.getCode();
        String openid = getOpenid(code);

        //根据openid查询用户信息
        User user = userMapper.getByOpenid(openid);

        if(user == null){
            //当前是一个新用户，自动完成注册
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDate.now());
            userMapper.insert(user);
        }
        return user;
    }

    /**
     * 获取微信用户的openid
     * @param code
     * @return
     */
    private String getOpenid(String code){
        //请求参数封装
        Map map = new HashMap();
        map.put("appid",appid);
        map.put("secret",secret);
        map.put("js_code",code);
        map.put("grant_type","authorization_code");

        //调用工具类，向微信接口服务发送请求
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        log.info("微信登录返回结果：{}", json);

        //解析json字符串
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        log.info("解析openid：{}", openid);

        return openid;
    }
}
