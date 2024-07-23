package org.arjunaoverdrive.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.orderservice.mapper.OrderMapper;
import org.arjunaoverdrive.orderservice.model.Order;
import org.arjunaoverdrive.orderservice.service.OrderMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderMessageService orderMessageService;


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order request){
        orderMessageService.submitOrderMessage(orderMapper.toOrderMessage(request));
        return ResponseEntity.ok().body("Order message has been sent to kafka");
    }
}
