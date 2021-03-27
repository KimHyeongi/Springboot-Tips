package com.tistory.eclipse4j.jpa.db1.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCompany implements Serializable {

    private String name;

    private String code;
}
