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
    public void wheneverDeiveryCompleted_DeliveryStatusChange(@Payload DeliveryCompleted deliveryCompleted){

        if(!deliveryCompleted.validate()) return;

        System.out.println("\n\n##### listener DeliveryStatusChange : " + deliveryCompleted.toJson() + "\n\n");

        Warehouse warehouse = warehouseRepository.findByProductId(deliveryCompleted.getProductId());
        warehouse.setProductQty(warehouse.getProductQty()-1);
        warehouseRepository.save(warehouse);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverServiceStrted_Warehousing(@Payload ServiceStarted serviceStarted){

        if(!serviceStarted.validate()) return;

        System.out.println("\n\n##### listener Warehousing : " + serviceStarted.toJson() + "\n\n");

        Warehouse warehouse = new Warehouse();
        warehouse.setProductId(serviceStarted.getProductId());
        warehouse.setProductName(serviceStarted.getProductName());
        warehouse.setProductQty(serviceStarted.getPlanQty());
        warehouseRepository.save(warehouse);
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}