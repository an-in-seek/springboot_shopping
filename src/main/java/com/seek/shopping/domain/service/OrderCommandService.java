package com.seek.shopping.domain.service;

import com.seek.shopping.domain.model.Delivery;
import com.seek.shopping.domain.model.DeliveryStatus;
import com.seek.shopping.domain.model.Item;
import com.seek.shopping.domain.model.Member;
import com.seek.shopping.domain.model.Order;
import com.seek.shopping.domain.model.OrderItem;
import com.seek.shopping.infrastructure.persistence.entity.OrderEntity;
import com.seek.shopping.infrastructure.persistence.mapper.ItemMapper;
import com.seek.shopping.infrastructure.persistence.mapper.MemberMapper;
import com.seek.shopping.infrastructure.persistence.mapper.OrderMapper;
import com.seek.shopping.infrastructure.persistence.repository.ItemJpaRepository;
import com.seek.shopping.infrastructure.persistence.repository.MemberJpaRepository;
import com.seek.shopping.infrastructure.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCommandService {

    private final OrderJpaRepository orderJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final ItemJpaRepository itemJpaRepository;

    public Long order(Long memberId, Long itemId, int count) {
        // 주문자 정보 조회
        Member member = memberJpaRepository.findById(memberId)
            .map(MemberMapper::toDomainModel)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 Member 입니다."));

        // 주문 Item 정보 조회
        Item item = itemJpaRepository.findById(itemId)
            .map(ItemMapper::toDomainModel)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 Item 입니다."));

        //배송정보 생성
        Delivery delivery = Delivery.of(member.getAddress(), DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        OrderEntity orderEntity = OrderMapper.toEntity(order);
        return orderJpaRepository.save(orderEntity).getId();
    }

    public void cancelOrder(Long orderId) {
        //주문 조회
        Order order = orderJpaRepository.findById(orderId)
            .map(OrderMapper::toDomainModel)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 Order 입니다."));

        //주문 취소
        order.cancel();
        orderJpaRepository.save(OrderMapper.toEntity(order));
    }
}
