package com.zfl.demo.domain.order;

import com.google.common.collect.Lists;
import com.zfl.demo.domain.account.Account;
import com.zfl.demo.domain.stock.Stock;
import com.zfl.demo.infrastructure.auth.service.SecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final SecurityService securityService;

    public OrderService(OrderRepository orderRepository, SecurityService securityService) {
        this.orderRepository = orderRepository;
        this.securityService = securityService;
    }

    public void create(Account account, Stock stock, Integer count, Integer money) {
        Order order = new Order();
        order.setAccount(account);
        order.setStock(stock);
        order.setCount(count);
        order.setMoney(money);
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return Lists.newArrayList(orderRepository.findAll());
    }

    public Page<Order> getList(String commodityName, int pageNum, int pageSize) {
        Specification<Order> specification =
                (root, criteriaQuery, criteriaBuilder) -> {
                    List<Predicate> predicates = new LinkedList<>();
                    if (StringUtils.isNotBlank(commodityName)) {

                        predicates.add(criteriaBuilder.like(root.get("stock").get("name"), "%" + commodityName + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                };
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
        return orderRepository.findAll(specification, pageable);
    }

    public Order getById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("order not found"));
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
