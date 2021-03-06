package com.qidian.mall.hystrix;

import com.central.base.restparam.RestResponse;
import com.qidian.mall.api.SysUserApi;
import com.qidian.mall.entity.SysUser;
import org.springframework.stereotype.Component;

/**
 * 熔断降级
 * @Author binsun
 * @Date
 * @Description
 */
@Component
public class SysUserApiHystrix extends BaseUserHystrix implements SysUserApi {
    @Override
    public RestResponse queryByUsername(String username) {
        return getError2();
    }

    @Override
    public RestResponse findByUsername(String username) {
        return getError();
    }

    @Override
    public RestResponse findByMobile(String mobile) {
        return getError();
    }

    @Override
    public RestResponse findByOpenId(String openId) {
        return getError();
    }
}
