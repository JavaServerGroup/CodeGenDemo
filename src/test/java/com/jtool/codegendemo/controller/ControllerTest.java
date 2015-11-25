package com.jtool.codegendemo.controller;

import com.alibaba.fastjson.JSON;
import com.jtool.codegendemo.api.request.SearchUserApiRequest;
import com.jtool.codegendemo.api.request.UploadAvatarRequest;
import com.jtool.codegendemo.api.response.SearchUserApiResponse;
import com.jtool.codegendemo.api.response.UploadAvatarResponse;
import com.jtool.test.AbstractControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@ContextConfiguration(locations = { "classpath:/application-context.xml"})
public class ControllerTest extends AbstractControllerTest {

	@Test
	public void testSearchUser() throws Exception {
		SearchUserApiRequest searchUserApiRequest = new SearchUserApiRequest();
		searchUserApiRequest.setIsMarried(true);
		searchUserApiRequest.setCountry("China");
		searchUserApiRequest.setAge(30);
		searchUserApiRequest.setHeight(1.87);

		String searchUserApiResponseStr = requestContentStringByGet("/searchUser", searchUserApiRequest);

		SearchUserApiResponse searchUserApiResponse = JSON.parseObject(searchUserApiResponseStr, SearchUserApiResponse.class);

		Assert.assertEquals("0", searchUserApiResponse.getCode());
		Assert.assertEquals(new Integer(100), searchUserApiResponse.getPages().getTotalPage());
		Assert.assertEquals("abcdefghijklmn", searchUserApiResponse.getPages().getVersion());

		List<String> urls = new ArrayList<>();
		urls.add("http://www.google.com");
		urls.add("http://www.facebook.com");

		Assert.assertEquals(urls, searchUserApiResponse.getUrls());

		Assert.assertEquals(new Double(1.56), searchUserApiResponse.getUsers().get(0).getHeight());
		Assert.assertEquals("用户1", searchUserApiResponse.getUsers().get(0).getName());
		Assert.assertEquals(new Integer(30), searchUserApiResponse.getUsers().get(0).getAge());
		Assert.assertEquals("China", searchUserApiResponse.getUsers().get(0).getCountry());
		Assert.assertEquals(true, searchUserApiResponse.getUsers().get(0).getIsMarried());

		Assert.assertEquals(new Double(1.70), searchUserApiResponse.getUsers().get(1).getHeight());
		Assert.assertEquals("用户2", searchUserApiResponse.getUsers().get(1).getName());
		Assert.assertEquals(new Integer(36), searchUserApiResponse.getUsers().get(1).getAge());
		Assert.assertEquals("China", searchUserApiResponse.getUsers().get(1).getCountry());
		Assert.assertEquals(false, searchUserApiResponse.getUsers().get(1).getIsMarried());
	}

	@Test
	public void testUploadAvatar() throws Exception {
		MockMultipartFile jpgFile = Utils.makeMockMultipartFile("file", "/media/j.jpg");

		UploadAvatarRequest uploadAvatarRequest = new UploadAvatarRequest();
		uploadAvatarRequest.setFile(jpgFile);
		uploadAvatarRequest.setMd5("xxxxxxxxxxxx");
		uploadAvatarRequest.setSeq(1);

		String uploadAvatarRequestStr = requestContentStringByPost("/uploadAvatar", uploadAvatarRequest);

		UploadAvatarResponse uploadAvatarResponse = JSON.parseObject(uploadAvatarRequestStr, UploadAvatarResponse.class);

		Assert.assertEquals("0", uploadAvatarResponse.getCode());
		Assert.assertEquals(Base64.getEncoder().encodeToString(jpgFile.getBytes()), uploadAvatarResponse.getFileContent());
	}
}
