package cn.panda.service;

import cn.panda.entity.Porn91;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyp
 * @since 2022-07-05
 */
public interface IPorn91Service extends IService<Porn91> {

    void spider(String url);

}
