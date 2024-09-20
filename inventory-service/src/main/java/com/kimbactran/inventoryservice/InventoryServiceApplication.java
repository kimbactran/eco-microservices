package com.kimbactran.inventoryservice;

import com.kimbactran.inventoryservice.model.Inventory;
import com.kimbactran.inventoryservice.repository.InventoryRepository;
import com.kimbactran.inventoryservice.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryService inventoryService, InventoryRepository inventoryRepository) {
//        return args -> {
//            Inventory inventory = Inventory.builder()
//                    .skuCode("disney_f8")
//                    .quantity(100)
//            .build();
//
//            Inventory inventory1 = Inventory.builder()
//                    .skuCode("disney_mouse")
//                    .quantity(0)
//                    .build();
//
//            inventoryRepository.save(inventory);
//            inventoryRepository.save(inventory1);
//        };
//    }

}
