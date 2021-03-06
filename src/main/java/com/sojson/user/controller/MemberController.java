package com.sojson.user.controller;

import java.util.List;
import java.util.Map;

import com.sojson.common.conf.CommResp;
import com.sojson.common.utils.RequestUtil;
import com.sojson.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.UUser;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.core.shiro.session.CustomSessionManager;
import com.sojson.user.bo.UserOnlineBo;
import com.sojson.user.service.UUserService;

/**
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * <p>
 * 用户会员管理
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月26日 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author zhou-baicheng
 * @version 1.0, 2016年5月26日 <br/>
 * @email i@itboy.net
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("member")
public class MemberController extends BaseController {
    /***
     * 用户手动操作Session
     * */
    @Autowired
    CustomSessionManager customSessionManager;
    @Autowired
    UUserService userService;

    /**
     * 用户列表管理
     *
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {

        map.put("findContent", findContent);
        Pagination<UUser> page = userService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("member/list");
    }

    /**
     * 跳转到添加用户界面
     *
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(UUser user) {
        //查看email是否填写，如果填写则表示将要保存用户。
        if (null != user && StringUtils.isNotBlank(user.getEmail())) {
            CommResp<UUser> commResp = userService.addMemberUser(user);
            if (RequestUtil.isSuccess(commResp)) {
                //添加成功，重定向到列表页
                return new ModelAndView("redirect:/member/list.shtml");
            } else {
                //添加失败
                ModelAndView modelAndView = new ModelAndView("member/add");
                modelAndView.addObject(commResp);
                return modelAndView;
            }
        } else {
            return new ModelAndView("member/add");
        }

    }

    /**
     * 在线用户管理
     *
     * @return
     */
    @RequestMapping(value = "online")
    public ModelAndView online() {
        List<UserOnlineBo> list = customSessionManager.getAllUser();
        return new ModelAndView("member/online", "list", list);
    }

    /**
     * 在线用户详情
     *
     * @return
     */
    @RequestMapping(value = "onlineDetails/{sessionId}", method = RequestMethod.GET)
    public ModelAndView onlineDetails(@PathVariable("sessionId") String sessionId) {
        UserOnlineBo bo = customSessionManager.getSession(sessionId);
        return new ModelAndView("member/onlineDetails", "bo", bo);
    }

    /**
     * 改变Session状态
     *
     * @param status
     * @param sessionIds
     * @return
     */
    @RequestMapping(value = "changeSessionStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeSessionStatus(Boolean status, String sessionIds) {
        return customSessionManager.changeSessionStatus(status, sessionIds);
    }

    /**
     * 根据ID删除，
     *
     * @param ids 如果有多个，以“,”间隔。
     * @return
     */
    @RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUserById(String ids) {
        return userService.deleteUserById(ids);
    }

    /**
     * 禁止登录
     *
     * @param id     用户ID
     * @param status 1:有效，0:禁止登录
     * @return
     */
    @RequestMapping(value = "forbidUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> forbidUserById(Long id, Long status) {
        return userService.updateForbidUserById(id, status);
    }

}
