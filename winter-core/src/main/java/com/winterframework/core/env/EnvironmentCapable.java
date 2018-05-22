/*
 * Copyright (c) 2018. HangZhou KET XuHekang, All rights reserved.
 * <http://1092181938@qq.com/>
 */

package com.winterframework.core.env;

/**
 * <p>EnvironmentCapable</p>
 *<p>用于获取环境值</p>
 * @author Hekang Xu (E-mail:1092181938@qq.com)
 * @since 2018/5/22
 */
public interface EnvironmentCapable {

    /**
     * 获取环境值
     * @return 环境值
     */
    Environment getEnvironment();
}
