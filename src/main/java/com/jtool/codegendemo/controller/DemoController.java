package com.jtool.codegendemo.controller;

import com.alibaba.fastjson.JSON;
import com.jtool.codegenannotation.CodeGenApi;
import com.jtool.codegenannotation.CodeGenRequest;
import com.jtool.codegenannotation.CodeGenResponse;
import com.jtool.codegendemo.api.request.SearchUserApiRequest;
import com.jtool.codegendemo.api.request.UploadAvatarApiRequest;
import com.jtool.codegendemo.api.response.Pages;
import com.jtool.codegendemo.api.response.SearchUserApiResponse;
import com.jtool.codegendemo.api.response.UploadAvatarApiResponse;
import com.jtool.codegendemo.api.response.User;
import com.jtool.codegendemo.exception.BackEndException;
import com.jtool.codegendemo.exception.ParamException;
import com.jtool.validator.ParamBeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.*;
import java.util.*;

@Controller
public class DemoController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @CodeGenApi(name = "查找用户", description = "根据用户国家，年纪，身高，是否结婚等条件过滤查找用户")
    @CodeGenRequest(SearchUserApiRequest.class)
    @CodeGenResponse(SearchUserApiResponse.class)
    @ResponseBody
    @RequestMapping(value = "/searchUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String searchUser(SearchUserApiRequest searchUserApiRequest) throws ParamException, BackEndException {


        log.debug("请求获得参数：" + searchUserApiRequest);

        log.debug("请求参数是否合法：" + !ParamBeanValidator.isNotValid(searchUserApiRequest));

        if(ParamBeanValidator.isNotValid(searchUserApiRequest)) {
            return "{\"code\":0}";
        }

        Pages pages = new Pages();
        pages.setTotalPage(100);
        pages.setVersion("abcdefghijklmn");

        List<String> urls = new ArrayList<>();
        urls.add("http://www.google.com");
        urls.add("http://www.facebook.com");

        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setHeight(searchUserApiRequest.getHeight());
        user.setName("用户1");
        user.setAge(searchUserApiRequest.getAge());
        user.setCountry(searchUserApiRequest.getCountry());
        user.setIsMarried(!searchUserApiRequest.getIsMarried().equals("0"));

        userList.add(user);

        User user1 = new User();
        user1.setHeight(searchUserApiRequest.getHeight());
        user1.setName("用户2");
        user1.setAge(searchUserApiRequest.getAge());
        user1.setCountry(searchUserApiRequest.getCountry());
        user1.setIsMarried(searchUserApiRequest.getIsMarried().equals("0"));

        userList.add(user1);

        SearchUserApiResponse searchUserApiResponse = new SearchUserApiResponse();
        searchUserApiResponse.setCode("0");
        searchUserApiResponse.setPages(pages);
        searchUserApiResponse.setUrls(urls);
        searchUserApiResponse.setUsers(userList);

        return JSON.toJSONString(searchUserApiResponse);
    }

    @CodeGenApi(name = "上传用户头像", description = "上传用户头像")
    @CodeGenRequest(UploadAvatarApiRequest.class)
    @CodeGenResponse(UploadAvatarApiResponse.class)
    @ResponseBody
    @RequestMapping(value = "/uploadAvatar", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String uploadAvatar(UploadAvatarApiRequest uploadAvatarRequest) throws ParamException, BackEndException, IOException {

        log.debug("请求获得参数：" + uploadAvatarRequest);

        log.debug("请求参数是否合法：" + !ParamBeanValidator.isNotValid(uploadAvatarRequest));

        if(ParamBeanValidator.isNotValid(uploadAvatarRequest)) {
            return "{\"code\":0}";
        }

        UploadAvatarApiResponse uploadAvatarResponse = new UploadAvatarApiResponse();
        uploadAvatarResponse.setCode("0");
        uploadAvatarResponse.setFileContent(Base64.getEncoder().encodeToString(uploadAvatarRequest.getFile().getBytes()));

        return JSON.toJSONString(uploadAvatarResponse);
    }

}