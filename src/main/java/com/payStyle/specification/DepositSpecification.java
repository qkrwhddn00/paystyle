package com.payStyle.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.payStyle.model.Deposit;

public class DepositSpecification {
	
	public static Specification<Deposit> searchTypeTitle(String searchKeyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cate"), "%" + searchKeyword + "%");
    }
	
    // 은행에 따른 검색
	public static Specification<Deposit> searchTypeWriter(String searchKeyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("bankName"), searchKeyword);
    }
	
    // 날짜 구분
	public static Specification<Deposit> betweenDate(Date startDate,Date endDate) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createDate"), startDate,endDate);
    }

}
