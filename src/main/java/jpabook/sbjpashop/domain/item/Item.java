package jpabook.sbjpashop.domain.item;

import jpabook.sbjpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// 한테이블에 다!!
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item { // 추상 클래스로 만든다

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


}
