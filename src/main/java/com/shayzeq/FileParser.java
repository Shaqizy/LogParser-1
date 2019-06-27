package com.shayzeq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {

    public static void reader(String filename, List<String> list) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;

        System.out.println("Reading from file...");

        while ((s = in.readLine()) != null){ //Считываем файл в List
            list.add(s);
        }
    }

    public static void parcer(List<String> list, List<String> list2, Map<String, String> map) {

        int thisRowNumber = 0;
        ArrayList<Integer> matchRowNumbers = new ArrayList<>();

        for (String s : list){  //Пробегаемся по массиву строк в List
            if (thisRowNumber == list.size()-1)
                thisRowNumber = list.size()-1;
            else{
                thisRowNumber++; //Счетчик строк
            }
            Matcher dateNtime = Pattern.compile
                    ("2018-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]").matcher(s);
            //Через регулярные выражения находим все даты

            while (dateNtime.find()) {

                if(list.get(thisRowNumber).matches("-{27,40}")) {

                    matchRowNumbers.add(thisRowNumber); //Массив номеров строк между которыми брать Sublist
                    list2.add(dateNtime.group()); //Записываем в List2 значения уникальных дат
                    Main.count++; //Количество уникальных дат и времени, имеющих ID
                }
            }
        }
            for (int i = 0; i < matchRowNumbers.size(); i++){ //Разбиваем массив номеров строк на текущее и предыдущее значение
            int z1 = matchRowNumbers.get(i); //Получаем индекс текущей совпавшей строки
            if(i == matchRowNumbers.size()-1){
                break;
            }
            else {
                int z2 = matchRowNumbers.get(i+1); //Получаем индекс следующей совпавшей строки
                map.put(list2.get(i),list.subList(z1,z2).toString()); //Передаем в HashMap дату лога в ключ
                for (String s : list.subList(z1,z2)){ // Сокращение программы в несколько раз
                    if(s.matches("ID: [0-9]{1,9}")){
                        String[] parts = s.split(": ");
                        String id = parts[1];
                    }
                }
                // и передаем Подстроку (Sublist) в значение, чтобы в дальнейшем ее распарсить на значения
                Main.countDateTimeList++;
            }

        }
        }
    }

