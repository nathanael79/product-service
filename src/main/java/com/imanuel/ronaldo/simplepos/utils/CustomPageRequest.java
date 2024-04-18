package com.imanuel.ronaldo.simplepos.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CustomPageRequest extends PageRequest {

    public CustomPageRequest(int page, int size, Sort sort) {
        super(page - 1, size, sort);
    }

    @Override
    public int getPageNumber() {
        return super.getPageNumber() + 1;
    }
}
