package ru.itpark.sb.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itpark.sb.JacksonConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonPractice {
    private final ObjectMapper objectMapper = JacksonConfiguration.initJackson();

    public JsonPractice() throws IOException {
    }

    private <T> List<T> readJsonFile(String filename, Class<T> type) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IOException("Файл не найден: " + filename);
            }

            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, type);
            return objectMapper.readValue(inputStream, listType);
        }
    }

    public void run() throws IOException {
        List<User> users = readJsonFile("users.json", User.class);
        List<Order> orders = readJsonFile("products.json", Order.class);

//        JSON-задача 1: Средний возраст по городам
        Map<String, Double> groupedUsers = users.stream()
                .collect(Collectors.groupingBy(
                        User::getCity,
                        Collectors.averagingInt(User::getAge)
                ));

        System.out.println("средний возраст по городам");
        System.out.println(groupedUsers);

//        Задача 2: Общая выручка по всем заказам
        double totalRevenue = orders.stream()
                .mapToDouble(Order::getSum)
                .sum();

        System.out.println("общая выручка");
        System.out.println(totalRevenue);

//        JSON-задача 3: Топ-продукт по выручке
//        JSON-задача 4: Количество заказов по пользователям (join двух файлов)
//        две задачки позже сделаю, надо разбираться с ними
    }
}
