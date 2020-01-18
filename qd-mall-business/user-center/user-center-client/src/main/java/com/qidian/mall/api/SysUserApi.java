package com.qidian.mall.api;

import com.central.base.restparam.RestResponse;
import com.qidian.mall.entity.CustomUserDetails;
import com.qidian.mall.hystrix.SysUserApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign 接口的url和web端的url要保持一致，否则调用feign接口的时候404，无法映射到web端
 *
 * 或者使用另外一个写法，单独抽一个公共api接口层，让生产者实现此接口，在此实现类实现相应业务逻辑，就不用url保持一致来绑定。
 * @Author binsun
 * @Date 2020-01-18
 * @Description
 */
@FeignClient(value = "qd-mall-usercenter",configuration = FeignClientsConfiguration.class,fallback = SysUserApiHystrix.class)
public interface SysUserApi {


    /**
     * 根据用户名查询用户-----
     * 这里为什么要把泛型实体加上，因为feign调用会将返回的body中的值，解析为LinkedHashMap 所以类型转换会失败
     * @param username
     * @return
     */
    @RequestMapping(value = "/sys-user/users-anon/login",method = RequestMethod.GET)
    RestResponse<CustomUserDetails> findByUsername(@RequestParam("username") String username);


    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/sys-user/users-anon/mobile", params = "mobile")
    RestResponse findByMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/sys-user/users-anon/openId", params = "openId")
    RestResponse findByOpenId(@RequestParam("openId") String openId);
}
