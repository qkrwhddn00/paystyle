package com.payStyle.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.payStyle.model.Withdraw;

public class AnalysisSpecification {
	
	public static Specification<Withdraw> searchTypeTitle(String searchKeyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cate"), "%" + searchKeyword + "%");
    }
	
    // 날짜 구분
	public static Specification<Withdraw> betweenDate(Date startDate,Date endDate) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createDate"), startDate,endDate);
    }

}
