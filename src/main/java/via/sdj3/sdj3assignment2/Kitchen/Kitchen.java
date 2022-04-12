package via.sdj3.sdj3assignment2.Kitchen;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import via.sdj3.sdj3assignment2.config.MessagingConfig;
import via.sdj3.sdj3assignment2.model.Order;
import via.sdj3.sdj3assignment2.repository.OrderRepository;

@Component
public class Kitchen {

    @Autowired
    OrderRepository orderRepository;

    @RabbitListener(queues = MessagingConfig.QUEUENAME)
    public void consumer(Order object){
        try {
            //after 10 sec change the order delivery to true
            Thread.sleep(10000);
            Order local = orderRepository.findById(object.getId()).orElseThrow(()->new RuntimeException("Order not found"));
            local.setDelivered(true);
            orderRepository.save(local);
            System.out.println("Order have been updated");
        }catch (InterruptedException e) {

        }
    }
}
