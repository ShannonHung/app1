package com.example.requestforwardapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder implements Serializable {

    private Integer iid;

    private List<LineItem> lineItems = new ArrayList<>();

    private String email;

    private String creditCard;

    private LocalDateTime checkoutDate;

    private String state="created";

}
