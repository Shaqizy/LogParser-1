package com.shayzeq;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FillDataModel {

    public static void findRequestResponce(Map<String, String> map, List<DataModel> list) throws IOException {

        //Регулярные выражения для поиска
        String regularID = "ID: [0-9]{1,10}"; //Поиск ID
        String requestType = "Http-Method: .{3,8}";
        String responceType = "Response-Code: [0-9]{3,5}";
        String methodName = "SOAPAction=\\[\"http.*"; //Поиск названия метода
        String regionAppkId = ".*<regionAppkId>(.*)</regionAppkId>.*"; //Поиск региона

        String id = null, request = null, responce = null, method = null, region = null;


        for (Map.Entry<String, String> entry : map.entrySet()) {

            String key = entry.getKey(); //Получение ключа (Даты) из HashMap Логов
            String value = entry.getValue(); //Получение строк между датами (инфо по каждому логу)
            Main.countDateTimeMap++;

            for (String info : value.split(", ")) {
                //System.out.println(info);
                if (info.matches(regularID)) {
                    String[] parts = info.split(": ");
                    id = parts[1];
                }
                if (info.matches(requestType)) {
                    String[] parts = info.split(": ");
                    request = parts[1];
                }
                if (info.matches(responceType)) {
                    String[] parts = info.split(": ");
                    responce = parts[1];
                    DataModel dataModel2 = new DataModel(id, responce, key);
                    list.add(dataModel2);
                }
                if (info.matches(methodName)) {
                    String[] parts = info.split("service/");
                    String[] subparts = parts[1].split("\"]}");
                    method = subparts[0];
                }
                if (info.matches(regionAppkId)) {
                    String[] parts = info.split("<regionAppkId>");
                    String[] subparts = parts[1].split("</regionAppkId>");
                    region = subparts[0];
                    DataModel dataModel = new DataModel(id,request, method, region, key);
                    list.add(dataModel);
                }
            }
        }
    }


}
