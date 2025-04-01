package com.zaz.servicevmoffers.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaz.servicevmoffers.event.OfferEvent;
import com.zaz.servicevmoffers.model.VmOffers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class VmOfferProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public static final String DTW_EXCHANGE_NAME = "serviceVmOffersToVmExchange";
    public static final String DTW_QUEUE_NAME = "serviceVmOffersToVmQueue";
    
    // Définition des constantes pour les types d'événements
    public static final String EVENT_CREATE = "CREATE";
    public static final String EVENT_UPDATE = "UPDATE";
    public static final String EVENT_DELETE = "DELETE";

    public void publishVmOffers(String eventType, VmOffers vmoffer) {
        try {
            OfferEvent event = new OfferEvent(); 
            event.setType(eventType);

            // Convertir l'opération en Map
            Map<String, Object> operationMap = objectMapper.convertValue(vmoffer, Map.class);
            event.setOffer(operationMap); 

            System.out.println("Publishing vmoffert event: " + event);
            
            // Publier vers différentes queues selon le type d'opération
            switch (event.getType()) {
                case EVENT_CREATE -> rabbitTemplate.convertAndSend(DTW_EXCHANGE_NAME, DTW_QUEUE_NAME, event);
                case EVENT_UPDATE -> rabbitTemplate.convertAndSend(DTW_EXCHANGE_NAME, DTW_QUEUE_NAME, event);
                case EVENT_DELETE -> rabbitTemplate.convertAndSend(DTW_EXCHANGE_NAME, DTW_QUEUE_NAME, event);
                default -> System.out.println("Unknown event type: " + event.getType());
            }
            
        } catch (Exception e) {
            System.err.println("Error publishing vmoffer event: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
