package cn.panda.spider.runnable;

import cn.panda.service.IPorn91Service;
import cn.panda.spider.task.SpiderStartEntry;
import cn.panda.entity.Porn91;
import cn.panda.entity.SpiderHistory;
import cn.panda.service.ISpiderHistoryService;
import cn.panda.spider.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PageProcessSingle implements Runnable {

    private String url;
    private IPorn91Service porn91Service;
    private String spiderFlag;

    private ISpiderHistoryService spiderHistoryService;

    public PageProcessSingle(String url, IPorn91Service porn91Service, String spiderFlag, ISpiderHistoryService spiderHistoryService) {
        this.url = url;
        this.porn91Service = porn91Service;
        this.spiderFlag = spiderFlag;
        this.spiderHistoryService = spiderHistoryService;
    }

    @Override
    public void run() {

        WebDriver driver = new ChromeDriver();    //Chrome浏览器
        driver.manage().window().setSize(new Dimension(1080, 500));

        try {
            driver.get(url);
        } catch (Exception e) {
            log.error("打开页面出错！===" + e.getMessage());
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 24; i++) {

            String href = null;
            String titleStr = null;
            String zuozheStr = null;
            String src = null;

            String pageXpath = "/html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div/a";
            String titleXpath = "html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div/a/span";
            String zuozheXpath = "/html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div";
            String imgUrlXpath = "html/body/div[4]/div[1]/div[3]/div/div/div[" + i + "]/div/a/div/img";

            By pageBy = null;
            By titleBy = null;
            By zuozheBy = null;
            By imgUrlBy = null;
            try {
                pageBy = By.xpath(pageXpath);
                titleBy = By.xpath(titleXpath);
                zuozheBy = By.xpath(zuozheXpath);
                imgUrlBy = By.xpath(imgUrlXpath);
            } catch (Exception e) {
                log.error("1=======>{}", e.getMessage());
            }

            try {
                WebElement pageUrl = driver.findElement(pageBy);
                href = pageUrl.getAttribute("href");

                WebElement title = driver.findElement(titleBy);
                titleStr = title.getText();

                WebElement zuozhe = driver.findElement(zuozheBy);
                zuozheStr = zuozhe.getText();

                WebElement imgUrl = driver.findElement(imgUrlBy);
                src = imgUrl.getAttribute("src");
            } catch (Exception e) {
                log.error("2=======>{}", e.getMessage());
            }

            if (StringUtils.isNotBlank(href)) {
                String pageUrl = Utils.processUrl(href);
                if (!SpiderStartEntry.urlSet.contains(pageUrl)) {

                    log.info("-----------------------------------------------");
                    log.info("titleStr={}", titleStr);
                    log.info("zuozheStr={}", zuozheStr);
                    log.info("-----------------------------------------------");

                    Porn91 porn91 = new Porn91();
                    porn91.setImgUrl(src);
                    porn91.setTitle(titleStr);
                    porn91.setVideoInfo(zuozheStr);
                    porn91.setPageUrl(pageUrl);

                    try {
                        porn91Service.save(porn91);
                        SpiderStartEntry.urlSet.add(pageUrl);
                        SpiderStartEntry.saveCount.getAndIncrement();
                    } catch (Exception e) {
                        log.error(" porn save fail :{}", e.getMessage());
                    }
                }
            }
        }

        SpiderHistory spiderHistory = new SpiderHistory();
        spiderHistory.setSpiderFlag(spiderFlag);
        spiderHistory.setUrl(url);
        spiderHistory.setLastTime(new Date());

        spiderHistoryService.save(spiderHistory);

        try {
            driver.quit();
        } catch (Exception e) {
            log.error("driver quit fail :{}", e.getMessage());
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("sleep fail");
        }

    }
}
