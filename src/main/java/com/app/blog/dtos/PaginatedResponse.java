package com.app.blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
public class PaginatedResponse<T> {
    private List<T> data;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalElements;
    private boolean isLast;
}
