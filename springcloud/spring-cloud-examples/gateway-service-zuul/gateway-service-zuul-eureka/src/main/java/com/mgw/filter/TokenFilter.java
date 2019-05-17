package com.mgw.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * 案例: 自定义Filter示例
 *
 * */
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {

        // 可以在请求被路由之前调用
        System.out.println("--------------------------1-------------------------------");
        return "pre";
    }

    @Override
    public int filterOrder() {

        // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
        System.out.println("--------------------------2-------------------------------");
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        // 是否执行该过滤器，此处为true，说明需要过滤
        System.out.println("--------------------------3-------------------------------");
        return true;
    }

    @Override
    public Object run() {

        System.out.println("--------------------------4-------------------------------");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        System.out.println("method: " + request.getMethod() + "  ,url: " + request.getRequestURL().toString());

        String token = request.getParameter("token");
        if (!StringUtils.isEmpty(token)) {

            currentContext.setSendZuulResponse(true); //对请求进行路由
            currentContext.setResponseStatusCode(200);
            currentContext.set("isSuccess",true);

            return null;

        }else {

            currentContext.setSendZuulResponse(false); //对请求进行路由
            currentContext.setResponseStatusCode(400);
            currentContext.setResponseBody("token is empty");
            currentContext.set("isSuccess",false);

            return null;
        }

    }
}
