package ru.itpark.sb.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApiPractice {
    List<Person> people = List.of(
            new Person("Володя", 21, "Москва", List.of("Играет на гитаре", "Поет")),
            new Person("Игорь", 15, "Волгоград", List.of("Играет в CS", "Хорошо учится")),
            new Person("Антон", 19, "Уфа", List.of("Водит машину", "Умеет готовить"))
    );
    List<Product> products = List.of(
            new Product("Яблоки", "Фрукты", 80.0),
            new Product("Бананы", "Фрукты", 150.0),
            new Product("Пепси", "Напитки", 250.0)
    );

    public void run()
    {
        //        1. Чётные числа, квадраты, фильтрация и сортировка
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * num)
                .filter(squareNumber -> squareNumber > 10)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(result);

//        2. Уникальные слова в нижнем регистре с сортировкой по длине
        List<String> words = List.of("Apple", "banana", "Cherry", "apple", "banana");
        List<String> sortedWords = words.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted(
                        Comparator.comparingInt(String::length)
                                .thenComparing(Comparator.naturalOrder())
                )
                .collect(Collectors.toList());

        System.out.println(sortedWords);

//        3. Имена совершеннолетних людей
        List<String> sortedPeopleName = people.stream()
                .filter(person -> person.getAge() >= 18)
                .map(Person::getName)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedPeopleName);

//        4. Средний возраст людей
        int averageAge = (int) people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);

        System.out.println(averageAge);

//        5. Группировка продуктов по категории
        Map<String, List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println(groupedProducts);

//        6. Проверка условий для списка продуктов
        boolean productsHavePositivePrice = products.stream()
                .allMatch(product -> product.getPrice() > 0);
        System.out.println("Все продукты имеют цену > 0: " + productsHavePositivePrice);

        boolean leastOneIsCheaper = products.stream()
                .anyMatch(product -> product.getPrice() < 100);
        System.out.println("Хотя бы один продукт дешевле 100: " + leastOneIsCheaper);

        boolean noneHaveZeroPrice = products.stream()
                .noneMatch(product -> product.getPrice() == 0);
        System.out.println("Ни один продукт не имеет цену 0: " + noneHaveZeroPrice);

//        7. Разбиение предложений на слова (flatMap)
        List<String> sentences = List.of(
                "Штормовой ветер и сильная метель парализовали дороги",
                "Эксперты советуют покупать ёлки в официальных точках и убирать через 10–12 дней",
                "Использование ЕС замороженных российских активов будет, по сути, объявлением войны, заявил Орбан"
        );
        List<String> processSentences = sentences.stream()
                .map(sentence -> sentence.replaceAll("[^a-zA-Zа-яА-Я\\s]", "")) // очистка от знаков препинания
                .map(sentence -> sentence.split("\\s+"))                                    // разбиение на слова
                .flatMap(Arrays::stream)                                                           // сведение в один поток
                .filter(word -> !word.isEmpty())                                                   // удаление пустых строк
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(processSentences);

//        8. Разделение людей на взрослых и детей
        Map<Boolean, List<Person>> partitionByAge = people.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 18));

        System.out.println(partitionByAge);

//        9. Суммарная стоимость продуктов по категориям
        Map<String, Double> sumPricesByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)
                ));

        System.out.println(sumPricesByCategory);

//        10. Топ-3 самых дешёвых продукта одной строкой
        String cheapestProducts = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .limit(3)
                .map(Product::getName)
                .collect(Collectors.joining(", "));

        System.out.println(cheapestProducts);
    }
}
