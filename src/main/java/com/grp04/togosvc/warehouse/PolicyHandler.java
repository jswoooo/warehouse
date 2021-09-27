package com.grp04.togosvc.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.grp04.togosvc.warehouse.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
    @Autowired WarehouseRepository warehouseRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeiveryCompleted_DeliveryStatusChange(@Payload DeliveryCompleted deiveryCompleted){

        if(!deiveryCompleted.validate()) return;

        System.out.println("\n\n##### listener DeliveryStatusChange : " + deiveryCompleted.toJson() + "\n\n");



        // Sample Logic //
        // Warehouse warehouse = new Warehouse();
        // warehouseRepository.save(warehouse);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverServiceStrted_Warehousing(@Payload ServiceStarted serviceStrted){

        if(!serviceStrted.validate()) return;

        System.out.println("\n\n##### listener Warehousing : " + serviceStrted.toJson() + "\n\n");



        // Sample Logic //
        // Warehouse warehouse = new Warehouse();
        // warehouseRepository.save(warehouse);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}