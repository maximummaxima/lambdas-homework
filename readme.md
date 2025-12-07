## Базовые сущности (для нескольких задач)

Рекомендованные классы

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;
    private int age;
    private String city;
    private List<String> skills;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Product {
    private String name;
    private String category;
    private double price;
}
```

---

## 10 задач по Stream API

### 1. Чётные числа, квадраты, фильтрация и сортировка

**Условие:**
Дан `List<Integer> numbers`.
Нужно:

1. Оставить только чётные числа.
2. Возвести их в квадрат.
3. Оставить только те квадраты, которые больше 10.
4. Отсортировать по убыванию.
5. Собрать результат в `List<Integer>`.

**Рекомендованные методы Stream:**

* `stream`
* `filter`
* `map`
* `sorted`
* `collect`

---

### 2. Уникальные слова в нижнем регистре с сортировкой по длине

**Условие:**
Дан `List<String> words`.
Нужно:

1. Привести все слова к нижнему регистру.
2. Убрать дубликаты.
3. Отсортировать по длине строки, при одинаковой длине — по алфавиту.
4. Вернуть `List<String>`.

**Рекомендованные методы Stream:**

* `stream`
* `map`
* `distinct`
* `sorted`
* `collect`

---

### 3. Имена совершеннолетних людей

**Условие:**
Дан `List<Person> people`.
Найти всех людей с возрастом не меньше 18, взять только их имена, отсортировать по алфавиту и вернуть `List<String>`.

**Рекомендованные методы Stream:**

* `stream`
* `filter`
* `map`
* `sorted`
* `collect`

---

### 4. Средний возраст людей

**Условие:**
Дан `List<Person> people`.
Посчитать средний возраст. Если список пуст, вернуть 0 или обработать это как ошибку (по твоему выбору).

**Рекомендованные методы Stream:**

* `stream`
* `mapToInt`
* `average`
* доп. методы `Optional*` (`orElse`, `orElseThrow` и т.п.)

---

### 5. Группировка продуктов по категории

**Условие:**
Дан `List<Product> products`.
Сгруппировать продукты по категории и получить `Map<String, List<Product>>`.

**Рекомендованные методы Stream:**

* `stream`
* `collect`
* `groupingBy`

---

### 6. Проверка условий для списка продуктов

**Условие:**
Дан `List<Product> products`.
Нужно:

1. Проверить, что **все** продукты имеют цену больше 0.
2. Проверить, что **хотя бы один** продукт дешевле 100.
3. Проверить, что **ни один** продукт не имеет цену ровно 0.

**Рекомендованные методы Stream:**

* `stream`
* `allMatch`
* `anyMatch`
* `noneMatch`

---

### 7. Разбиение предложений на слова (flatMap)

**Условие:**
Дан `List<String> sentences`, каждое — предложение.
Нужно:

1. Очистить строки от лишних знаков (по желанию).
2. Разбить каждую строку на слова.
3. Собрать все слова в один поток.
4. Удалить пустые строки.
5. Убрать дубликаты.
6. Отсортировать по алфавиту.
7. Вернуть `List<String>`.

**Рекомендованные методы Stream:**

* `stream`
* `map`
* `flatMap`
* `filter`
* `distinct`
* `sorted`
* `collect`

---

### 8. Разделение людей на взрослых и детей

**Условие:**
Дан `List<Person> people`.
Разделить всех на две группы:

* совершеннолетние (возраст ≥ 18),
* несовершеннолетние (возраст < 18).

Результат: `Map<Boolean, List<Person>>`, где `true` — взрослые, `false` — дети.

**Рекомендованные методы Stream:**

* `stream`
* `collect`
* `partitioningBy`

---

### 9. Суммарная стоимость продуктов по категориям

**Условие:**
Дан `List<Product> products`.
Посчитать суммарную цену всех продуктов в каждой категории: `Map<String, Double>`.

**Рекомендованные методы Stream:**

* `stream`
* `collect`
* `groupingBy`
* `summingDouble`

---

### 10. Топ-3 самых дешёвых продукта одной строкой

**Условие:**
Дан `List<Product> products`.
Найти 3 самых дешёвых продукта, взять их имена и соединить в одну строку через запятую, например
`"Apple, Banana, Milk"`.

**Рекомендованные методы Stream:**

* `stream`
* `sorted`
* `limit`
* `map`
* `collect`
* `joining`

---

## Задачи на парсинг JSON через Jackson + агрегирование

### Файлы лежат в src/main/resources

Классы необходимо описать самим

### JSON-задача 1: Средний возраст по городам

**Условие:**
Считать `users.json` в `List<User>` и посчитать средний возраст пользователей для каждого города.
Результат: `Map<String, Double>`.

**Рекомендованные методы Jackson:**

* `ObjectMapper`
* `readValue`
* `TypeReference<List<User>>`

**Рекомендованные методы Stream:**

* `stream`
* `collect`
* `groupingBy`
* `averagingInt`

---

### JSON-задача 2: Общая выручка по всем заказам

**Условие:**
Считать `orders.json` в `List<Order>` и посчитать общую сумму всех заказов по полю `sum`.

**Рекомендованные методы Jackson:**

* `ObjectMapper`
* `readValue`
* `TypeReference`

**Рекомендованные методы Stream:**

* `stream`
* `mapToDouble`
* `sum`

---

### JSON-задача 3: Топ-продукт по выручке

**Условие:**
По данным из `orders.json` найти продукт с максимальной суммарной выручкой.
Выручка по продукту = `price * qty`, затем суммируется по всем заказам.
Результат: название продукта и величина выручки.

**Рекомендованные методы Jackson:**

* `ObjectMapper`
* `readValue`
* `TypeReference`

**Рекомендованные методы Stream:**

* `stream`
* `flatMap`
* `collect`
* `groupingBy`
* `summingDouble`
* `entrySet`
* `max`
* `comparingByValue`

---

### JSON-задача 4: Количество заказов по пользователям (join двух файлов)

**Условие:**
Считать `users.json` и `orders.json`.
Нужно получить `Map<String, Long>`, где ключ — имя пользователя, значение — количество его заказов.

Шаги:

1. Прочитать пользователей в `List<User>`.
2. Прочитать заказы в `List<Order>`.
3. Построить отображение `id пользователя → User`.
4. По списку заказов сгруппировать по имени пользователя и посчитать количество заказов.

**Рекомендованные методы Jackson:**

* `ObjectMapper`
* `readValue`
* `TypeReference`

**Рекомендованные методы Stream:**

* для пользователей:

    * `stream`
    * `collect`
    * `toMap`
* для заказов:

    * `stream`
    * `collect`
    * `groupingBy`
    * `counting`
