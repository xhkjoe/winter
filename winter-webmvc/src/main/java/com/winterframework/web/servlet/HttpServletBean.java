/*
 * Copyright (c) 2018. HangZhou KET XuHekang, All rights reserved.
 * <http://1092181938@qq.com/>
 */

package com.winterframework.web.servlet;
import javax.servlet.http.HttpServlet;
import com.winterframework.context.EnvironmentAware;
import com.winterframework.core.env.ConfigurableEnvironment;
import com.winterframework.core.env.Environment;
import com.winterframework.core.env.EnvironmentCapable;

/**
 * <p>HttpServletBean</p>
 *<p>提供初始化httpservlet的模板</p>
 * @author Hekang Xu (E-mail:1092181938@qq.com)
 * @since 2018/5/22
 */
public abstract class HttpServletBean extends HttpServlet implements EnvironmentAware{
}
