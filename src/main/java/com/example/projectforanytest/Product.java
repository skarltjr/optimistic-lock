package com.example.projectforanytest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private int stock = 1;

    @Version
    private int version=0;

    //주문을 통한 재고 감소
    public void orderProduct(int counts) {
        // order에서 먼저 남은 재고 검증(checkUsableStock)을 통해 주문 -> 동시적으로 주문이 일어난 경우를 대비해 주문 처리할 때 한번 더 검증
        int remainingStock = this.stock;
        if (remainingStock < counts) {
            throw new IllegalArgumentException("현재 남은 재고가 주문량보다 적습니다.");
        }

        this.stock -= counts;
        if (this.stock < 0) {
            throw new IllegalArgumentException("재고는 음수일 수 없다");
        }
    }

}

