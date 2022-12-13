package com.example.demo_get.service.implement;


import com.example.demo_get.model.in.BotIn;
import com.example.demo_get.model.respond.UserRespond;
import com.example.demo_get.repostory.UserRepository;
import com.example.demo_get.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Random;

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
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserRespond insert(BotIn botIn) {
        ratioSell= botIn.getRatioSell();
        timeSleep = botIn.getTimeSleep();
        desirStockPrice =botIn.getDesirStockPrice();
        times = botIn.getTimes();
        return null;
    }

    @Override
    @Scheduled(fixedDelayString = "PT1s")
    public UserRespond commandBot() {//ty le mua ban
        if(desirStockPrice == 0) {
            return null;
        } else {
            Random random = new Random();
            Boolean isSale = random.nextBoolean();
            int ratio = random.nextInt(100); //0-99
            if ( ratio <= ratioSell ){
                isSale = true ;
            }else
                isSale = false ;
            String[] userName = {"Trung","Lang","Minh","Huy"};
            int stockNumber = random.nextInt(51);
            if(conditions < times) {
                for (  int i=  0  ; i <= times ; i ++ ){
                    int stockPrice = desirStockPrice- times + i  ;
                    conditions = i;
                    userRepository.insertCommand(userName[random.nextInt(4)], "cow", isSale,stockPrice,stockNumber  );
                    try {
                        Thread.sleep(timeSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                int stockPrice = desirStockPrice ;
                userRepository.insertCommand(userName[random.nextInt(4)], "cow", isSale,stockPrice,stockNumber  );
                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
