package com.moss.controller;

import com.moss.common.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 表单登录模块
 */
@RestController
public class FormController {

    private RequestCache cache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 需要身份认证的时候，告知前台需要登录，让前台跳转
     */
    @RequestMapping("/authtication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse needAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = cache.getRequest(request,response);

        if(null != savedRequest){ String targerUrl = savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(targerUrl, ".html")){
                redirectStrategy.sendRedirect(request, response, targerUrl);
            }
        }

        return new SimpleResponse("该请求需要身份认证", savedRequest.getRedirectUrl());
    }

}
