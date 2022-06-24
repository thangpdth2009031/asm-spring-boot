package com.example.asmspringboot.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key; // Trường cần so sanh ví dụ là Id
    private String operation; // Toán tử so sánh >,<, =
    private Object value; // Giá trị cần so sánh
    // Vd: chung Id = 1; Id là key, = là operation, 1 là value
}
