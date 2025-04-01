package com.zaz.servicevmoffers.event;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfferEvent {
    @JsonProperty("type")
    private String type; // CREATE, UPDATE, DELETE
    
    @JsonProperty("offer")
    private Map<String, Object> offer  = null;

}
