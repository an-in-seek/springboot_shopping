package com.seek.shopping.application;

import com.seek.shopping.domain.service.OrderCommandService;
import com.seek.shopping.domain.service.OrderQueryService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    public Long order(Long memberId, Long itemId, int count) {
        return orderCommandService.order(memberId, itemId, count);
    }

    public void cancelOrder(Long orderId) {
        orderCommandService.cancelOrder(orderId);
    }

    public Optional<OrderDto> getOrderById(Long orderId) {
        return orderQueryService.getOrderById(orderId).map(OrderDto::from);
    }
}
