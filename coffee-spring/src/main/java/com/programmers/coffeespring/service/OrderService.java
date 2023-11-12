package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.OrderItems;
import com.programmers.coffeespring.dto.OrderRequestDto;
import com.programmers.coffeespring.exception.CafeException;
import com.programmers.coffeespring.exception.ErrorCode;
import com.programmers.coffeespring.model.Order;
import com.programmers.coffeespring.model.Voucher;
import com.programmers.coffeespring.model.VoucherValid;
import com.programmers.coffeespring.repository.OrderRepository;
import com.programmers.coffeespring.repository.ProductRepository;
import com.programmers.coffeespring.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final VoucherRepository voucherRepository;
    public void order(OrderRequestDto requestDto) {
        checkingProduct(requestDto);
        checkingTotalPrice(requestDto);
        Order order = Order.createOrder(requestDto);
        orderRepository.insert(order);
    }

    public void orderWithVoucher(OrderRequestDto requestDto) {
        checkingProduct(requestDto);
        checkingTotalPrice(requestDto);
        checkingVoucher(requestDto.getVoucherId());
        Order order = Order.createOrder(requestDto);
        orderRepository.insertWithVoucher(order);
    }

    private void checkingVoucher(Long voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new CafeException(ErrorCode.VOUCHER_NOT_FOUND));
        if(voucher.getVoucherValid() != VoucherValid.VALID){
            throw new CafeException(ErrorCode.VOUCHER_NOT_VALID);
        }
    }

    private static void checkingTotalPrice(OrderRequestDto requestDto) {
        Long sum = requestDto.getOrderItems()
                .stream()
                .map(orderItems -> orderItems.price() * orderItems.quantity())
                .reduce(0L, Long::sum);
        if(sum != requestDto.getTotalPrice()){
            throw new CafeException(ErrorCode.INVALID_TOTAL_PRICE);
        }
    }

    private void checkingProduct(OrderRequestDto requestDto) {
        requestDto.getOrderItems()
                .stream()
                .map(OrderItems::product_id)
                .map(productRepository::findById)
                .findFirst()
                .orElseThrow(() -> new CafeException(ErrorCode.PRODUCT_NOT_FOUND));
    }

}
