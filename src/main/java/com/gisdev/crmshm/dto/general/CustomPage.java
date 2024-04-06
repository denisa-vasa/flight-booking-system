package com.gisdev.crmshm.dto.general;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomPage<T> {

    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalElements;

    public CustomPage(Page<T> page) {
        data = page.getContent();
        currentPage = page.getNumber();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
    }
}
