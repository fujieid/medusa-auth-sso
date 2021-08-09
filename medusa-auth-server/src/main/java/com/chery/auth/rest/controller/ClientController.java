/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ClientControllerImpl
 * Author:   Allen
 * Date:     2020/8/15
 * Description: 客户端rest接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.rest.controller;

import com.chery.auth.client.constant.AuthServiceConstant;
import com.chery.auth.client.query.ClientQuery;
import com.chery.auth.core.service.impl.ClientServiceImpl;
import com.chery.common.util.StringUtils;
import com.fujieid.jap.ids.model.ClientDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 客户端rest接口
 *
 * @author Allen
 * @date 2020-07-10
 */
@Slf4j
@ApiIgnore
@Controller
@RequestMapping("/client")
@SessionAttributes("clientList")
public class ClientController {
    /**
     * clientService注入
     */
    @Autowired
    private ClientServiceImpl clientService;

    /**
     * 获取每一个服务下面实例
     * 测试时，可以使用多个端口启动服务实例
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 客户端管理页面跳转
     *
     * @param model <model>
     * @return 客户端详情信息
     */
    @GetMapping(value = "/list")
    public String clientLists(Model model) {
        List<ClientDetail> clientList = clientService.getAllClientDetail();
        model.addAttribute("clientList", clientList);
        return "client/client_list";
    }

    /**
     * 客户端添加
     *
     * @param model model
     * @return 添加客户端
     */
    @GetMapping("/addView")
    public String addClient(Model model) {
        List<String> services = discoveryClient.getServices();
        model.addAttribute("addClient", "add");
        model.addAttribute("serviceList", services);
        return "client/client_add_edit";
    }

    /**
     * 根据客户端ID查询客户端
     *
     * @param modelType 类型（查询还是编辑）
     * @param clientId  客户端ID
     * @return 客户端信息
     */
    @GetMapping(value = "/detail")
    public ModelAndView getClientByClientId(String clientId, String modelType) {
        ClientDetail clientDetail = clientService.getByClientId(clientId);
        ModelAndView view = new ModelAndView();
        view.addObject("detail", clientDetail);
        if (StringUtils.isNotNull(modelType) && modelType.equals(AuthServiceConstant.AUTH_MODEL_TYPE)) {
            List<String> services = discoveryClient.getServices();
            view.addObject("serviceList", services);
            view.setViewName("client/client_add_edit");
        } else {
            view.setViewName("client/client_detail");
        }
        return view;
    }

    /**
     * 客户端新增
     *
     * @param clientQuery 客户端信息
     * @return 操作结果
     */
    @PostMapping(value = "/save")
    public ModelAndView saveClient(ClientQuery clientQuery) {
        ModelAndView view = new ModelAndView();
        ClientDetail clientDetail = clientService.getByClientId(clientQuery.getClientId());
        if (clientDetail == null) {
            clientService.insert(clientQuery);
        } else {
            clientService.update(clientDetail.getId(), clientQuery);
        }
        view.setViewName("redirect:list");
        return view;
    }

    /**
     * 客户端删除
     *
     * @param clientId 客户端Id
     * @return 操作结果
     */
    @GetMapping(value = "/remove")
    public ModelAndView deleteClient(String clientId) {
        clientService.removeByClientId(clientId);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:list");
        return view;
    }

}
