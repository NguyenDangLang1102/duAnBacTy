package com.example.demo_get.service.implement;

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
    private static int allBotPrice = 10000;
    @Autowired
    private UserRepository userRepository;

    @Override
//    @Scheduled(cron = "0 0 */1 * * *")
    @Scheduled(fixedRate = 2000)
    public UserRespond commandBot() {
        System.out.println("▂▃▄▅▆▇█▓▒░GET░▒▓█▇▆▅▄▃▂");

        Random random = new Random();
        String[] userName = {"Trung","Lang","Minh","Huy"};
        Boolean isSale = random.nextBoolean();
        int stockPrice = random.nextInt(11) + 20;
        int stockNumber = random.nextInt(100);
        int realBotPrice = allBotPrice - stockNumber*stockPrice;//so tien con lai
        allBotPrice = realBotPrice;
        System.out.println("gggggg" +allBotPrice);
        if (allBotPrice > (stockNumber*stockPrice)){
            userRepository.insertCommand(userName[random.nextInt(4)], "cow", isSale,stockPrice,stockNumber  );
            return null;
        }else {
            return null;

        }

    }
}
