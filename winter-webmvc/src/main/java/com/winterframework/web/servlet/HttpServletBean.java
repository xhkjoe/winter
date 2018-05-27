/*
 * Copyright (c) 2018. HangZhou KET XuHekang, All rights reserved.
 * <http://1092181938@qq.com/>
 */

package com.winterframework.web.servlet;
import com.winterframework.core.env.EnvironmentAware;
import com.winterframework.core.env.EnvironmentCapable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.winterframework.beans.BeanWrapper;
import org.winterframework.beans.BeansException;
import org.winterframework.beans.MutablePropertyValues;
import org.winterframework.beans.PropertyAccessorFactory;
import org.winterframework.beans.PropertyValue;
import org.winterframework.beans.PropertyValues;
import org.winterframework.context.EnvironmentAware;
import org.winterframework.core.env.ConfigurableEnvironment;
import org.winterframework.core.env.Environment;
import org.winterframework.core.env.EnvironmentCapable;
import org.winterframework.core.io.Resource;
import org.winterframework.core.io.ResourceEditor;
import org.winterframework.core.io.ResourceLoader;
import org.winterframework.util.Assert;
import org.winterframework.util.StringUtils;
import org.winterframework.web.context.support.ServletContextResourceLoader;
import org.winterframework.web.context.support.StandardServletEnvironment;

/**
 * <p>HttpServletBean</p>
 *<p>提供初始化httpservlet的模板</p>
 * @author Hekang Xu (E-mail:1092181938@qq.com)
 * @since 2018/5/22
 */
public abstract class HttpServletBean extends HttpServlet implements EnvironmentCapable, EnvironmentAware{

    protected final Log logger = LogFactory.getLog(getClass());


    /**
     * 初始化
     * @throws ServletException
     */
    @Override
    public final void init() throws ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Initializing servlet '" + getServletName() + "'");
        }

        //设置bean
        try {
            PropertyValues pvs = new ServletConfigPropertyValues(getServletConfig(), this.requiredProperties);
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
            ResourceLoader resourceLoader = new ServletContextResourceLoader(getServletContext());
            bw.registerCustomEditor(Resource.class, new ResourceEditor(resourceLoader, getEnvironment()));
            initBeanWrapper(bw);
            bw.setPropertyValues(pvs, true);
        }
        catch (BeansException ex) {
            logger.error("Failed to set bean properties on servlet '" + getServletName() + "'", ex);
            throw ex;
        }

        // 初始化servletbean
        initServletBean();

        if (logger.isDebugEnabled()) {
            logger.debug("Servlet '" + getServletName() + "' configured successfully");
        }
    }


    private static class ServletConfigPropertyValues extends MutablePropertyValues {

        /**
         * Create new ServletConfigPropertyValues.
         * @param config ServletConfig we'll use to take PropertyValues from
         * @param requiredProperties set of property names we need, where
         * we can't accept default values
         * @throws ServletException if any required properties are missing
         */
        public ServletConfigPropertyValues(ServletConfig config, Set<String> requiredProperties)
                throws ServletException {

            Set<String> missingProps = (requiredProperties != null && !requiredProperties.isEmpty()) ?
                    new HashSet<String>(requiredProperties) : null;

            Enumeration<String> en = config.getInitParameterNames();
            while (en.hasMoreElements()) {
                String property = en.nextElement();
                Object value = config.getInitParameter(property);
                addPropertyValue(new PropertyValue(property, value));
                if (missingProps != null) {
                    missingProps.remove(property);
                }
            }

            // Fail if we are still missing properties.
            if (missingProps != null && missingProps.size() > 0) {
                throw new ServletException(
                        "Initialization from ServletConfig for servlet '" + config.getServletName() +
                                "' failed; the following required properties were missing: " +
                                StringUtils.collectionToDelimitedString(missingProps, ", "));
            }
        }
    }

}
