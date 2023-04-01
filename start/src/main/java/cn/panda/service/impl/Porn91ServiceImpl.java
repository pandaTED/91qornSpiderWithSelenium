package cn.panda.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.panda.mapper.Porn91Mapper;
import cn.panda.service.IPorn91Service;
import cn.panda.spider.util.Utils;
import cn.panda.entity.Porn91;
import cn.panda.entity.SpiderHistory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.panda.service.ISpiderHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyp
 * @since 2022-07-05
 */
@Service
@Slf4j
public class Porn91ServiceImpl extends ServiceImpl<Porn91Mapper, Porn91> implements IPorn91Service {

    @Resource
    private ISpiderHistoryService spiderHistoryService;

    @Override
    public void spider(String url) {

        WebDriver driver = new ChromeDriver();    //Chrome浏览器
        log.info("开始！！！！！");

        try {
            driver.get(url);
        } catch (Exception e) {
            log.error("打开页面出错！==="+e.getMessage());
        }

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        String s = driver.getPageSource().toString();

        try {
            FileUtils.write(new File("index-"+ IdUtil.fastUUID() +".html"), s, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("读取页面");

        List<Porn91> porn91List = new ArrayList<>();

        for (int i = 1; i <= 24; i++) {

            log.info("循环！！！！");

            String pageXpath = "/html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div/a";
            String titleXpath = "html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div/a/span";
            String zuozheXpath = "/html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div";
            String imgUrlXpath = "html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div/a/div/img";

            By pageBy = By.xpath(pageXpath);
            By titleBy = By.xpath(titleXpath);
            By zuozheBy = By.xpath(zuozheXpath);
            By imgUrlBy = By.xpath(imgUrlXpath);

            WebElement pageUrl = driver.findElement(pageBy);
            String href = pageUrl.getAttribute("href");

            WebElement title = driver.findElement(titleBy);
            String titleStr = title.getText();

            WebElement zuozhe = driver.findElement(zuozheBy);
            String zuozheStr = zuozhe.getText();

            WebElement imgUrl = driver.findElement(imgUrlBy);
            String src = imgUrl.getAttribute("src");

            log.info("-----------------------------------------------");
            log.info("href");
            log.info("titleStr");
            log.info("zuozheStr");
            log.info("src");
            log.info("-----------------------------------------------");

            Porn91 porn91 = new Porn91();
            porn91.setImgUrl(src);
            porn91.setTitle(titleStr);
            porn91.setVideoInfo(zuozheStr);
            porn91.setPageUrl(Utils.processUrl(href));

            porn91List.add(porn91);
        }


        log.info("保存！！！！");

        try {
            this.saveBatch(porn91List);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        SpiderHistory spiderHistory = new SpiderHistory();
        spiderHistory.setSpiderFlag("----");
        spiderHistory.setUrl(url);
        spiderHistory.setLastTime(new Date());

        log.info("历史保存！！！！");

        spiderHistoryService.save(spiderHistory);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("退出退出退出退出退出！");
        driver.quit();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
