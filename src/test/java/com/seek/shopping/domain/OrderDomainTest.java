package com.seek.shopping.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.seek.shopping.IntegrationTest;
import com.seek.shopping.domain.model.Money;
import com.seek.shopping.domain.model.Order;
import com.seek.shopping.domain.model.OrderStatus;
import com.seek.shopping.domain.service.OrderCommandService;
import com.seek.shopping.domain.service.OrderQueryService;
import com.seek.shopping.infrastructure.persistence.entity.AddressEntity;
import com.seek.shopping.infrastructure.persistence.entity.ItemEntity;
import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import com.seek.shopping.infrastructure.persistence.repository.ItemJpaRepository;
import com.seek.shopping.infrastructure.persistence.repository.MemberJpaRepository;
import com.seek.shopping.infrastructure.persistence.repository.OrderJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("주문(Order) 도메인 테스트")
class OrderDomainTest extends IntegrationTest {

    @Autowired
    private OrderCommandService orderCommandService;

    @Autowired
    private OrderQueryService orderQueryService;

    private static final String MEMBER_NAME = "seek";
    private static final String MEMBER_EMAIL = "seek1@naver.com";
    private static final String ITEM_NAME = "name";
    private static final Money ITEM_PRICE = Money.from(1000);
    private static final int ITEM_STOCK_QUANTITY = 10;
    private static final int ORDER_COUNT = 3;

    private Long memberId;
    private Long itemId;

    @BeforeEach
    void setUp(
        @Autowired MemberJpaRepository memberJpaRepository,
        @Autowired ItemJpaRepository itemJpaRepository
    ) {
        memberId = saveMember(memberJpaRepository);
        itemId = saveItem(itemJpaRepository);
    }

    @AfterEach
    void cleanUp(
        @Autowired OrderJpaRepository orderJpaRepository,
        @Autowired MemberJpaRepository memberJpaRepository
    ) {
        orderJpaRepository.deleteAll();
        memberJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("주문 테스트")
    void testOrder() {
        // when
        final Long orderId = orderCommandService.order(memberId, itemId, ORDER_COUNT);
        final Optional<Order> order = orderQueryService.getOrderById(orderId);

        // then
        assertThat(order).isPresent();
        assertThat(order.get()).isNotNull();
        assertThat(order.get().getId()).isNotNull().isEqualTo(orderId);
        assertThat(order.get().getMember().getId()).isNotNull().isEqualTo(memberId);
        assertThat(order.get().getMember().getEmail()).isNotNull().isEqualTo(MEMBER_EMAIL);
        assertThat(order.get().getMember().getName()).isNotNull().isEqualTo(MEMBER_NAME);
        assertThat(order.get().getOrderItems()).isNotEmpty().hasSize(1);
        assertThat(order.get().getOrderItems().get(0).getItem().getId()).isNotNull().isEqualTo(itemId);
        assertThat(order.get().getOrderItems().get(0).getItem().getName()).isNotNull().isEqualTo(ITEM_NAME);
        assertThat(order.get().getOrderItems().get(0).getItem().getPrice()).isNotNull().isEqualTo(ITEM_PRICE);
        assertThat(order.get().getOrderItems().get(0).getItem().getStockQuantity()).isNotNull().isEqualTo(ITEM_STOCK_QUANTITY);
        assertThat(order.get().getOrderItems().get(0).getOrderPrice()).isNotNull().isEqualTo(ITEM_PRICE);
        assertThat(order.get().getOrderItems().get(0).getCount()).isNotNull().isEqualTo(ORDER_COUNT);
        assertThat(order.get().getOrderItems().get(0).getAmounts()).isNotNull().isEqualTo(ITEM_PRICE.multiply(ORDER_COUNT));
        assertThat(order.get().getOrderStatus()).isNotNull().isEqualTo(OrderStatus.PAYMENT_WAITING);
        assertThat(order.get().getTotalAmounts()).isNotNull().isEqualTo(ITEM_PRICE.multiply(ORDER_COUNT));
        assertThat(order.get().getOrderDate()).isNotNull();
    }

    @Test
    @DisplayName("주문 조회 테스트")
    void testFindOrder() {
        // given
        final Long orderId = orderCommandService.order(memberId, itemId, ORDER_COUNT);

        // when
        final Optional<Order> order = orderQueryService.getOrderById(orderId);

        // then
        assertThat(order).isPresent();
        assertThat(order.get()).isNotNull();
        assertThat(order.get().getId()).isNotNull().isEqualTo(orderId);
        assertThat(order.get().getMember().getId()).isNotNull().isEqualTo(memberId);
        assertThat(order.get().getMember().getEmail()).isNotNull().isEqualTo(MEMBER_EMAIL);
        assertThat(order.get().getMember().getName()).isNotNull().isEqualTo(MEMBER_NAME);
        assertThat(order.get().getOrderItems()).isNotEmpty().hasSize(1);
        assertThat(order.get().getOrderItems().get(0).getItem().getId()).isNotNull().isEqualTo(itemId);
        assertThat(order.get().getOrderItems().get(0).getItem().getName()).isNotNull().isEqualTo(ITEM_NAME);
        assertThat(order.get().getOrderItems().get(0).getItem().getPrice()).isNotNull().isEqualTo(ITEM_PRICE);
        assertThat(order.get().getOrderItems().get(0).getItem().getStockQuantity()).isNotNull().isEqualTo(ITEM_STOCK_QUANTITY);
        assertThat(order.get().getOrderItems().get(0).getOrderPrice()).isNotNull().isEqualTo(ITEM_PRICE);
        assertThat(order.get().getOrderItems().get(0).getCount()).isNotNull().isEqualTo(ORDER_COUNT);
        assertThat(order.get().getOrderItems().get(0).getAmounts()).isNotNull().isEqualTo(ITEM_PRICE.multiply(ORDER_COUNT));
        assertThat(order.get().getOrderStatus()).isNotNull().isEqualTo(OrderStatus.PAYMENT_WAITING);
        assertThat(order.get().getTotalAmounts()).isNotNull().isEqualTo(ITEM_PRICE.multiply(ORDER_COUNT));
        assertThat(order.get().getOrderDate()).isNotNull();
    }

    @Test
    @DisplayName("주문 취소 테스트")
    void testOrderCancel() {
        // given
        final Long orderId = orderCommandService.order(memberId, itemId, ORDER_COUNT);

        // when
        orderCommandService.cancelOrder(orderId);
        final Optional<Order> order = orderQueryService.getOrderById(orderId);

        // then
        assertThat(order).isPresent();
        assertThat(order.get()).isNotNull();
        assertThat(order.get().getId()).isNotNull().isEqualTo(orderId);
        assertThat(order.get().getMember().getId()).isNotNull().isEqualTo(memberId);
        assertThat(order.get().getMember().getEmail()).isNotNull().isEqualTo(MEMBER_EMAIL);
        assertThat(order.get().getMember().getName()).isNotNull().isEqualTo(MEMBER_NAME);
        assertThat(order.get().getOrderItems()).isNotEmpty().hasSize(1);
        assertThat(order.get().getOrderItems().get(0).getItem().getId()).isNotNull().isEqualTo(itemId);
        assertThat(order.get().getOrderItems().get(0).getItem().getName()).isNotNull().isEqualTo(ITEM_NAME);
        assertThat(order.get().getOrderItems().get(0).getItem().getPrice()).isNotNull().isEqualTo(ITEM_PRICE);
        assertThat(order.get().getOrderItems().get(0).getItem().getStockQuantity()).isNotNull().isEqualTo(ITEM_STOCK_QUANTITY);
        assertThat(order.get().getOrderItems().get(0).getOrderPrice()).isNotNull().isEqualTo(ITEM_PRICE);
        assertThat(order.get().getOrderItems().get(0).getCount()).isNotNull().isEqualTo(ORDER_COUNT);
        assertThat(order.get().getOrderItems().get(0).getAmounts()).isNotNull().isEqualTo(ITEM_PRICE.multiply(ORDER_COUNT));
        assertThat(order.get().getOrderStatus()).isNotNull().isEqualTo(OrderStatus.CANCELLED);
        assertThat(order.get().getTotalAmounts()).isNotNull().isEqualTo(ITEM_PRICE.multiply(ORDER_COUNT));
        assertThat(order.get().getOrderDate()).isNotNull();
    }

    private Long saveMember(MemberJpaRepository memberJpaRepository) {
        return memberJpaRepository.saveAndFlush(
            MemberEntity.builder()
                .name(MEMBER_NAME)
                .email(MEMBER_EMAIL)
                .address(createAddress())
                .build()
        ).getId();
    }

    private Long saveItem(ItemJpaRepository itemJpaRepository) {
        return itemJpaRepository.saveAndFlush(
            ItemEntity.builder()
                .name(ITEM_NAME)
                .price(ITEM_PRICE.value())
                .stockQuantity(ITEM_STOCK_QUANTITY)
                .build()
        ).getId();
    }

    private AddressEntity createAddress() {
        return AddressEntity.builder()
            .city("city")
            .street("street")
            .zipcode("zipcode")
            .build();
    }
}
