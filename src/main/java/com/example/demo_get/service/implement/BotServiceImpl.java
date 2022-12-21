package com.example.demo_get.service.implement;


import com.example.demo_get.model.entity.AccountEntity;
import com.example.demo_get.model.in.BotIn;
import com.example.demo_get.model.respond.CommandRespond;
import com.example.demo_get.repostory.AccountRepository;
import com.example.demo_get.repostory.CommandRepository;
import com.example.demo_get.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Component
@Transactional



@Configuration
@EnableScheduling
public class BotServiceImpl implements BotService {
    private static int ratioSell = 0;//ty le ban
    private static int timeSleep = 0;// tg nghi
    private static  int desirStockPrice = 0;// gia mong muon
    private static  int times = 0;// so lan day gia

    private static int conditions = 0;

    private static LocalDateTime timeStart   ;

    private static LocalDateTime timeStop  ;
    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;//import

    @Autowired
    private AccountRepository accountRepository;



    @Autowired
    private CommandRepository commandRepository;



    @Override
    public CommandRespond insert(BotIn botIn) {

        ratioSell= botIn.getRatioSell();
        timeSleep = botIn.getTimeSleep();
        desirStockPrice =botIn.getDesirStockPrice();
        times = botIn.getTimes();
        timeStop= botIn.getTimeStop();
        timeStart= botIn.getTimeStart();
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    @Scheduled(fixedDelayString = "PT1h")
    public CommandRespond commandBot() {//ty le mua ban


            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
              List<AccountEntity> list = accountRepository.getAccount();
//               commandRepository.insertCommand("trung", "cow", true,12,16  );

                    System.out.println("Task executed : ");

                }
            };
            // Schedule the task to run every 2 seconds
            timer.schedule(task, 0, 2000);

            // Stop the task after 3 seconds
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.cancel();
            timer.cancel();



//        if(desirStockPrice == 0) {
//            return null;
//        } else {
//            Random random = new Random();
//            Boolean isSale = random.nextBoolean();
//            System.out.println("timeStart"+ timeStart);
//            System.out.println("timeStop"+ timeStop);
//            int ratio = random.nextInt(100); //0-99
//            if ( ratio <= ratioSell ){
//                isSale = true ;
//            }else
//                isSale = false ;
//            String[] userName = {"Trung","Lang","Minh","Huy"};
//            int stockNumber = random.nextInt(51);
//            if(conditions < times) {
//                for (  int i=  0  ; i <= times ; i ++ ){
//                    int stockPrice = desirStockPrice- times + i  ;
//                    conditions = i;
//                    commandRepository.insertCommand(userName[random.nextInt(4)], "cow", isSale,stockPrice,stockNumber  );
//                    try {
//                        Thread.sleep(timeSleep);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }else {
//                System.out.println("vao else");
//                int stockPrice = desirStockPrice ;
//                commandRepository.insertCommand(userName[random.nextInt(4)], "cow", isSale,stockPrice,stockNumber  );
//                try {
//                    Thread.sleep(timeSleep);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return null;
    }
}
