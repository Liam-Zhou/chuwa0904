package org.fionagu.restdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Long id;
    private  String name;
    private  int age;
    private String payment;
    private LocalDate orderDate;
    private String address;
}
