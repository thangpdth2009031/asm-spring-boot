package com.example.asmspringboot.specification;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBody {
    private String roadName;
    private int districtId;
    private int status;
}
