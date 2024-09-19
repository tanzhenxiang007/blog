package com.blog.玩玩.Springboot设置多数据源_随时切换;

import com.blog.dao.TypeDao;
import com.blog.pojo.Type;
import com.blog.玩玩.Springboot设置多数据源_随时切换.util.DataSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/dbsave")
public class dbsave {
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private DataSourceConfig DataSourceConfig;


    @RequestMapping("/getEs")
    @ResponseBody
    public void save(){
        Type type = new Type();
        type.setName("测试");
        type.setId(111L);
        int i = typeDao.saveType(type);
        if (i==1){
            System.out.println("数据源1保存成功");
        }
      //切换数据源
        DataSourceUtil.setDB("db2");
        int i1 = typeDao.saveType(type);
        if (i1==1){
            System.out.println("数据源2保存成功");
        }

        DataSourceConfig.dataSourceOne();
    }
}
