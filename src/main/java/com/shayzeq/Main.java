package com.shayzeq;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.LongSummaryStatistics;

import java.util.*;

public class Main {
    public static int count = 0;
    public static int countDateTimeList = 0;
    public static int countDateTimeMap = 0;
    public static void main(String[] args) throws IOException {

        String fileName = "/Users/igorfrolov/Desktop/testor.txt";
        ArrayList<String> allStringsFromLog = new ArrayList<>();
        ArrayList<String> dateFromLog = new ArrayList<>();
        TreeMap<String, String> parsedFile = new TreeMap<>();
        ArrayList<DataModel> dataModelList = new ArrayList<>();
        ArrayList<DataModel> preFinalDataModelList = new ArrayList<>();
        Set<String> uniqMethod = new HashSet<>();
        ArrayList<FinalDataModel> dataExcel = new ArrayList<>();

        FileParser.reader(fileName, allStringsFromLog);


        FileParser.parcer(allStringsFromLog, dateFromLog, parsedFile);
        System.out.println("Количество запросов в Log файле = " + count);

        //ExcelWriter.writeToExcel(parsedFile);
        String request = "2018-06-28 14:41:35,871";
        String responce = "2018-06-28 14:41:36,525";
        long asap = TimeParseAndCalculate.timeCalculator(request, responce);
        System.out.println("Время запроса = " + asap);

//        DataModel dt = new DataModel("1","getBalance", 7, 654);
//        List<DataModel> dataModelList = new ArrayList<>();
//        for (String s : allStringsFromLog){
//            DataModel dataModel = new DataModel(s,s,s,s); //Запись данных в лист создержащий DataModel тип
//            dataModelList.add(dataModel);
//        }

        FillDataModel.findRequestResponce(parsedFile, dataModelList);
//        System.out.println("ReusltList = "+countDateTimeList);
//        System.out.println("ResultMap = "+countDateTimeMap);

//        for (DataModel dm : dataModelList ){
//            if(dm.getRequestType()!=null){
//                System.out.printf("ID = %s, Request = %s, Method = %s, Appkid = %s, DateTime = %s",
//                        dm.getId(), dm.getRequestType(),dm.getMethodName(),dm.getRegionAppkId(),dm.getDateTime());
//                System.out.println();
//            }else {
//                System.out.printf("ID = %s, Responce = %s, DateTime = %s",
//                        dm.getId(), dm.getResponceType(),dm.getDateTime());
//                System.out.println();
//            }
//        }
        for (int i = 0; i < dataModelList.size(); i++) {
            if(i == dataModelList.size()-1){
                break;
            }else {
                if (dataModelList.get(i).getId().equals(dataModelList.get(i+1).getId())){
                    long res = TimeParseAndCalculate.timeCalculator(dataModelList.get(i).getDateTime(), dataModelList.get(i+1).getDateTime());
                    DataModel dml = new DataModel(dataModelList.get(i).getId(), dataModelList.get(i).getMethodName(),
                            dataModelList.get(i).getRegionAppkId(), res);
                    preFinalDataModelList.add(dml);
                    System.out.println(dml.toString());
//                    System.out.printf("ID = %s, Request = %s, Responce = %s, Method = %s, Appkid = %s, WorkTime = %d ms",
//                            dataModelList.get(i).getId(), dataModelList.get(i).getRequestType(),
//                            dataModelList.get(i+1).getResponceType(), dataModelList.get(i).getMethodName(),
//                            dataModelList.get(i).getRegionAppkId(), res);
//                    System.out.println();
                }
            }
        }

//        for (DataModel dtml : preFinalDataModelList){
//            System.out.println(dtml.toString());
//        }


        for (int i = 0; i < preFinalDataModelList.size(); i++) {
            if(i == preFinalDataModelList.size()-1){
                break;
            }
            else {
                if(preFinalDataModelList.get(i).getMethodName().equals(preFinalDataModelList.get(i+1).getMethodName()) &&
                preFinalDataModelList.get(i).getRegionAppkId().equals(preFinalDataModelList.get(i+1).getRegionAppkId())){
                    String methodAppkid = preFinalDataModelList.get(i).getMethodName() + "/" + preFinalDataModelList.get(i).getRegionAppkId();
                    //System.out.println("Первый прогон: " + methodAppkid);
                    uniqMethod.add(methodAppkid);
                }
            }
        }

        for (String s : uniqMethod){
           // System.out.println("Второй прогон: " + s);
        }
        HashMap<String, ArrayList<Long>> finalMap = new HashMap<>();

        for (String s: uniqMethod){
            finalMap.put(s,new ArrayList<Long>());
        }



        for (int i = 0; i < preFinalDataModelList.size(); i++) {
            if(i == preFinalDataModelList.size()-1){
                break;
            }
            else {
                String check = preFinalDataModelList.get(i).getMethodName() + "/" + preFinalDataModelList.get(i).getRegionAppkId();
                for (String s : uniqMethod){
                    if(s.equals(check)){
                        finalMap.get(s).add(preFinalDataModelList.get(i).getWorkTime());
                    }
                }
            }
        }

        for(Map.Entry<String, ArrayList<Long>> entry : finalMap.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue().toString();

            System.out.println(key);
            System.out.println(value);
        }

        for(Map.Entry<String, ArrayList<Long>> entry : finalMap.entrySet()) {

            String key = entry.getKey();
            String[] parts = key.split("/");
            ArrayList<Long> value = entry.getValue();
            double sum = 0;
            for(long l : value){
                sum+=l;
            }
            String methodName = parts[0];
            //System.out.println();
            String appkId = parts[1];
            double avgWorkTime = sum/value.size();
            long maxWorkTime = Collections.max(value);
            long minWorkTime = Collections.min(value);
            FinalDataModel fdm = new FinalDataModel(methodName, appkId, avgWorkTime, maxWorkTime, minWorkTime);
            dataExcel.add(fdm);
        }

        ExcelWriter.writeToExcel(dataExcel);

    }
}
