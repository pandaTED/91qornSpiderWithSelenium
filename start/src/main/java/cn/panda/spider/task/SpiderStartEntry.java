package cn.panda.spider.task;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.panda.entity.Porn91;
import cn.panda.service.IPorn91Service;
import cn.panda.service.ISpiderHistoryService;
import cn.panda.spider.runnable.PageProcessSingle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SpiderStartEntry {

    @Resource
    private IPorn91Service porn91Service;

    @Resource
    private ISpiderHistoryService spiderHistoryService;

    public static volatile Set<String> urlSet = new ConcurrentHashSet<>();
    public static volatile AtomicInteger saveCount = new AtomicInteger(0);

//    @Scheduled(initialDelay = 10000, fixedDelay = 3600000)
    public void init() {

        String spiderFlag = IdUtil.fastSimpleUUID();

        String pageStart = "https://91porn.com/v.php?page=";

        Integer threadSize = 8;

        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);

        List<Porn91> list = porn91Service.list();
        Set<String> collect = list.stream().map(Porn91::getPageUrl).collect(Collectors.toSet());

        urlSet.addAll(collect);

        for (int y = 0; y < 2; y++) {
            for (int i = 400; i < 700; i++) {
                String str2 = pageStart + i;
                PageProcessSingle pageProcessSingle = new PageProcessSingle(str2, porn91Service, spiderFlag, spiderHistoryService);
                executorService.submit(pageProcessSingle);
            }
        }

        executorService.shutdown();

        try {
            while (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                log.info("本次新增 ：{}", saveCount.get());
                log.info("本次新增 ：{}", saveCount.get());
                log.info("本次新增 ：{}", saveCount.get());
                log.info("本次新增 ：{}", saveCount.get());
                log.info("本次新增 ：{}", saveCount.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
