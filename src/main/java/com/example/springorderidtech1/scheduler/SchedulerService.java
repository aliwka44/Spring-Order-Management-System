package com.example.springorderidtech1.scheduler;

import com.example.springorderidtech1.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final PaymentService service;
    //Runs every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void payMonthlyFree(){
       service.payMonthlyFree();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void runDailyAtMight(){
        //System.out.println("Daily at might");
    }
}
