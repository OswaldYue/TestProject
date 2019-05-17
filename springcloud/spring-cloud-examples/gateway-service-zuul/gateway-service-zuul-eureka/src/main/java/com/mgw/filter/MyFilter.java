package com.mgw.filter;

import com.netflix.zuul.ZuulFilter;

public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {

        //定义filter的类型，有pre、route、post、error四种
        return "pre";
    }

    @Override
    public int filterOrder() {
        //定义filter的顺序，数字越小表示顺序越高，越先执行
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        //表示是否需要执行该filter，true表示执行，false表示不执行
        return true;
    }

    @Override
    public Object run() {
        //filter需要执行的具体操作
        return null;
    }
}
