package com.sojson.common.utils;

import com.sojson.common.conf.BusinessCode;
import com.sojson.common.conf.CommResp;

/**
 * Created by wangkuan on 2017/4/11.
 */
public final class RequestUtil {
    private RequestUtil() {
    }

    /**
     * 判断是否处理成功
     *
     * @param commResp
     * @return
     */
    public static boolean isSuccess(CommResp commResp) {
        return (null != commResp && commResp.getCode().equalsIgnoreCase(BusinessCode.SUCCESS.getCode()));
    }

}
