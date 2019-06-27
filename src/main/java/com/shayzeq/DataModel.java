package com.shayzeq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DataModel {

    private String id; //ID запроса
    private String requestType; //Тип запроса
    private String responceType; //Тип ответа
    private String methodName; //Название метода запроса (Например getPaymentsB2B)
    private String regionAppkId; //Цифра, обозначающая регион на который пришел запрос (Например 8801 - центральный офис)
    private long workTime; //Время работы запроса в милисекундах (Время между Request и Responce)
    private String dateTime; //Дата Время запроса

}
