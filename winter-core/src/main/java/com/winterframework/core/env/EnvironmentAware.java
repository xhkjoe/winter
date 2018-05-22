/*
 * Copyright (c) 2018. HangZhou KET XuHekang, All rights reserved.
 * <http://1092181938@qq.com/>
 */

package com.winterframework.core.env;

/**
 * <p>EnvironmentAware</p>
 *<p>改变环境值</p>
 * @author Hekang Xu (E-mail:1092181938@qq.com)
 * @since 2018/5/22
 */
public interface EnvironmentAware {

    /**
     * 设置环境值
     * @param environment 需要设置的环境值
     */
    void setEnvironment(Environment environment);
}
