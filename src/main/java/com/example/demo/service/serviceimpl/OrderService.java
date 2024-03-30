package com.example.demo.service.serviceimpl;

import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    //    @Transactional
    public OrderItem save(Long n) {
        OrderItem orderItem = new OrderItem();
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();
        OrderItem orderItem3 = new OrderItem();
        OrderItem orderItem4 = new OrderItem();
        OrderItem orderItem5 = new OrderItem();
        OrderItem orderItem6 = new OrderItem();
        OrderItem orderItem7 = new OrderItem();
        OrderItem orderItem8 = new OrderItem();

        orderItem.setId(n);
        orderItem1.setId(n);
        orderItem2.setId(n);
        orderItem3.setId(n);
        orderItem4.setId(n);
        orderItem5.setId(n);
        orderItem6.setId(n);
        orderItem7.setId(n);
        orderItem8.setId(n);

        orderItem1.setItemName(String.valueOf(new Date().getTime()));
        orderItem.setItemName(String.valueOf(new Date().getTime()) + new Date().getTime());
        orderItem2.setItemName(UUID.randomUUID().toString());
        orderItem3.setItemName(UUID.randomUUID().toString());
        orderItem4.setItemName(UUID.randomUUID().toString());
        orderItem5.setItemName(UUID.randomUUID().toString());
        orderItem6.setItemName(UUID.randomUUID().toString());
        orderItem7.setItemName(UUID.randomUUID().toString());
        orderItem8.setItemName(UUID.randomUUID().toString());


        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);
        orderItemList.add(orderItem4);
        orderItemList.add(orderItem5);
        orderItemList.add(orderItem6);
        orderItemList.add(orderItem7);
        orderItemList.add(orderItem8);

        for (OrderItem item : orderItemList) {
            new Thread(() -> orderRepository.save(item)).start();
        }
        System.out.println(orderItem1.getId());
        return orderItem1;
    }


}
