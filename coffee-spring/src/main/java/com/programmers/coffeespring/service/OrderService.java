package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.OrderItems;
import com.programmers.coffeespring.dto.OrderRequestDto;
import com.programmers.coffeespring.exception.CafeException;
import com.programmers.coffeespring.exception.ErrorCode;
import com.programmers.coffeespring.model.Order;
import com.programmers.coffeespring.model.Voucher;
import com.programmers.coffeespring.model.VoucherType;
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
        Voucher voucher = checkingVoucher(requestDto.getVoucherId());
        checkingTotalPriceWithVoucher(requestDto, voucher);
        Order order = Order.createOrder(requestDto);
        orderRepository.insertWithVoucher(order);
    }

    private void checkingTotalPrice(OrderRequestDto requestDto) {
        Long sum = requestDto.getOrderItems()
                .stream()
                .map(orderItems -> orderItems.price() * orderItems.quantity())
                .reduce(0L, Long::sum);
        if(sum != requestDto.getTotalPrice()){
            throw new CafeException(ErrorCode.INVALID_TOTAL_PRICE);
        }
    }

    private Voucher checkingVoucher(Long voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new CafeException(ErrorCode.VOUCHER_NOT_FOUND));
        if(voucher.getVoucherValid() != VoucherValid.VALID){
            throw new CafeException(ErrorCode.VOUCHER_NOT_VALID);
        }
        return voucher;
    }

    private void checkingTotalPriceWithVoucher(OrderRequestDto requestDto, Voucher voucher) {
        Long sum = requestDto.getOrderItems()
                .stream()
                .map(orderItems -> orderItems.price() * orderItems.quantity())
                .reduce(0L, Long::sum);
        sum = voucherUsedSum(voucher, sum);
        if(sum != requestDto.getTotalPrice()){
            throw new CafeException(ErrorCode.INVALID_TOTAL_PRICE);
        }
    }

    private static Long voucherUsedSum(Voucher voucher, Long sum) {
        if(voucher.getVoucherType() == VoucherType.FIXED) {
            sum -= voucher.getDiscountValue();
        }
        if(voucher.getVoucherType() == VoucherType.PERCENT){
            sum = sum *(100 - voucher.getDiscountValue());
        }
        return sum;
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
