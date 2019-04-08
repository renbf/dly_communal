package com.project.framework.config;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.appinterface.domain.GiftApply;
import com.project.appinterface.service.GiftMachineService;

/**
 * 
 * @author rbf
 *
 */
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {
	private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);
	@Autowired
	GiftMachineService giftMachineService;

	// 3.添加定时任务
	@Scheduled(cron = "0 0 1 * * ?")
	// 或直接指定时间间隔，例如：5秒
	private void configureTasks() {
		log.info("1点定时任务开始");
		List<GiftApply> overdueList = giftMachineService.selectGiftIdOverdue();
		if (CollectionUtils.isNotEmpty(overdueList)) {
			for (GiftApply giftApply : overdueList) {
				try {
					giftMachineService.refundDeposit(giftApply.getGiftId(), giftApply.getUserId());
				} catch (Exception e) {
					log.error("1点定时任务异常:giftId={},userId={}",giftApply.getGiftId(),giftApply.getUserId(),e);
				}
			}
		}
	}
}
