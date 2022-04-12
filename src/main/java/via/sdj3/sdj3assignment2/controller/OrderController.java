package via.sdj3.sdj3assignment2.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.sdj3assignment2.config.MessagingConfig;
import via.sdj3.sdj3assignment2.model.Order;
import via.sdj3.sdj3assignment2.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order")
    public Order bookOrder(@RequestBody Order order){
        System.out.println("Order placed");

        Order local = orderRepository.save(order);

        template.convertAndSend(MessagingConfig.EXCHANGENAME,MessagingConfig.ROUTING_KEY,local);
        return local;
    }

    @GetMapping("/orders")
    public List<Order> getAll()
    {
        return (List<Order>) orderRepository.findAll();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable(value = "id")int id)
    {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        return ResponseEntity.ok().body(order);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") int id, @RequestBody Order order){

        Order local = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        local.setAmount(order.getAmount());
        local.setDelivered(order.isDelivered());
        local.setDescription(order.getDescription());
        orderRepository.save(local);
        return ResponseEntity.ok().body(local);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable(value = "id") int id){
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
