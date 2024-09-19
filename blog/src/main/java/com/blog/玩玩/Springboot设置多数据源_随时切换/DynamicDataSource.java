package com.blog.玩玩.Springboot设置多数据源_随时切换;

import com.blog.玩玩.Springboot设置多数据源_随时切换.util.DataSourceUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 动态数据源类
 * @date 2022/2/11
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override    // 确定当前要使用的数据源
    protected Object determineCurrentLookupKey() {
        return DataSourceUtil.getDB();
    }
}

