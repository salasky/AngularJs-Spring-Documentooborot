package com.example.testproject1.service.staticList;

import java.util.ArrayList;
import java.util.List;

public class StaticList {
    public static List<String> docNameList=new ArrayList<>(List.of("Привет Мир!", "Уфа","Казань","Питер","Москва"));

    public static List<String> docNameIncomingList=new ArrayList<>(List.of("Главный документ", "Отпуск","Больничный","Выходной","Увольнение"));

    public static List<String> docNameOutgoingList=new ArrayList<>(List.of("Архив", "Отдел кадров","Отдел программирования","Отдел производства","Маркетинг"));
    public static List<String> docTextList=new ArrayList<>(List.of("IamDocText-Hello worl!", "IamDocText-Java","IamDocText-Text","IamDocText-Kotlin","IamDocText-Spring"));

    public static List<String> docAuthorList=new ArrayList<>(List.of("Бабошин Игорь Сергеевич", "Дацюк Павел Иванович","Кучеров Никита Сергеевич","Кросби Сидни Иванович","Джобс Стив Олегович"));

    public static List<String> controlList=new ArrayList<>(List.of("Подтверждение автора","Проверка тестировщика","Не назначено"));

    public static List<String> controlPersonList=new ArrayList<>(List.of("Иванов Игорь Сергеевич", "Кавальчук Алексей Иванович","Гвардиола Пеп Сергеевич"));

    public static List<String> distinPersonList =new ArrayList<>(List.of("Иванов Иван Иванович", "Админ Админ Админовчи","Гвардиола Пеп Сергеевич","Rik Nikols Pool","Gleen Satoshi Nikamota"));

    public static List<String> deliveryTypeList =new ArrayList<>(List.of("Голубями", "Email","RocketChat","Гонцом","Почтой России"));
}
