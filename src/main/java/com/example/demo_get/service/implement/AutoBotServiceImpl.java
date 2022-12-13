package com.example.demo_get.service.implement;

import com.example.demo_get.model.in.AutoBotIn;
import com.example.demo_get.model.respond.UserRespond;
import com.example.demo_get.repostory.UserRepository;
import com.example.demo_get.service.AutobotService;
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

public class AutoBotServiceImpl implements AutobotService {
    private static int allBotPrice = 0;
    private static int timeSleep = 0;
    private static int maxStockVolume = 0;
    private static int minStockVolume = 0;



    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRespond insert(AutoBotIn autoBotIn) {
        allBotPrice= autoBotIn.getAllBotPrice();
        timeSleep = autoBotIn.getTimeSleep();
        if (autoBotIn.getMinStockVolume() <= autoBotIn.getMaxStockVolume()){
            maxStockVolume = autoBotIn.getMaxStockVolume();
            minStockVolume = autoBotIn.getMinStockVolume();
            return null;
        }else {
            return new UserRespond("Min ?");
        }
    }

    @Override
    @Scheduled(cron = "0 0/15 9-13/45 * * *")
    @Scheduled(fixedDelayString = "PT2s")

    //tao ra cac command chay deu 15/lan tu 9h-18h
    public UserRespond commandAutoBot() {// start va stop thu cong
        if (allBotPrice == 0){
            return null;
        }else {
            Random random = new Random();
            String[] userName = {"Trung", "Lang", "Minh", "Huy"};
            String[] codeStock  = {"cow", "pig", "horse", "rooster", "hen","dog","cat","donkey"};
            Boolean isSale = random.nextBoolean();
            int stockPrice = random.nextInt(11) + 20;// random.nextInt(max + 1 - min) + min
            int stockNumber = random.nextInt(maxStockVolume + 1 - minStockVolume) + minStockVolume;
            int realBotPrice = allBotPrice - stockNumber * stockPrice;//so tien con lai
            if (allBotPrice > (stockNumber * stockPrice)) {
                userRepository.insertCommand(userName[random.nextInt(2)], codeStock[random.nextInt(8)], isSale, stockPrice, stockNumber);
                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            } else {
                return null;
            }
        }
    }




}
