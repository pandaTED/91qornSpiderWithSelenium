package cn.panda.controller;

import cn.panda.entity.Porn91;
import cn.panda.service.IPorn91Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyp
 * @since 2022-07-05
 */
@Controller
@Slf4j
public class IndexController {

    @Resource
    private IPorn91Service porn91Service;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {

        String keyword = request.getParameter("keyword");

        List<Porn91> list = new ArrayList<>();

        if (StringUtils.isNotBlank(keyword)) {
            LambdaQueryWrapper<Porn91> porn91LambdaQueryWrapper = Wrappers.lambdaQuery(Porn91.class);
            porn91LambdaQueryWrapper.select(Porn91::getTitle, Porn91::getImgUrl, Porn91::getTags, Porn91::getPageUrl)
                    .notLike(Porn91::getTitle, "付费")
                    .notLike(Porn91::getTitle, "收费")
                    .notLike(Porn91::getTags, "wenzi")
                    .and(item -> item.like(Porn91::getTags, keyword).or().like(Porn91::getTitle, keyword))
                    .orderByDesc(Porn91::getId);

            list = porn91Service.list(porn91LambdaQueryWrapper);
        }

        model.addAttribute("list", list);

        return "index";
    }

}
