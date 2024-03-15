package com.example.demosmartjust.entity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class ParamUtil {

    public static Pageable parseString(PageableParam pageableParam) {
        Pageable pageable;
        if (!pageableParam.getSort().equals("UNSORTED")) {
            String direction = pageableParam.getSort().split(":")[1];
            Sort.Order order = new Sort.Order(Sort.Direction.valueOf(direction.trim()), pageableParam.getSort().split(":")[0]);
            pageable = PageRequest.of(pageableParam.getPageIndex(), pageableParam.getPageSize(), Sort.by(order));
        } else {
            pageable = PageRequest.of(pageableParam.getPageIndex(), pageableParam.getPageSize());
        }
        return pageable;
    }
}
