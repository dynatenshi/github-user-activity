# GitHub User Activity CLI
Простое консольное приложение для отслеживания последней активности пользователя на GitHub. 
Проект выполнен в рамках учебного задания по работе с внешними API и парсингом JSON.

Задание:
https://roadmap.sh/projects/github-user-activity

# Особенности
<ul>
<li>Использование Java 17 и встроенного HttpClient</li>
<li>Парсинг данных с помощью библиотеки Gson</li>
<li>Сборка в единый исполняемый Fat JAR файл</li>
<li>Обработка ошибок (неверный пользователь, ошибки API)</li>
</ul>

# Требования
<ul>
<li>Java JDK 17+</li>
<li>Maven (для сборки)</li>
</ul>

# Установка и сборка
<ol>
<li>
Скачайте исходный код или
склонируйте репозиторий:

```bash 
clone https://github.com/dynatenshi/github-user-activity.git
```

</li>

<li>Откройте терминал в папке проекта и выполните сборку:

```bash
mvn clean package
```

</li>
</ol>

После сборки в папке **target** появится файл **github-user-activity-1.0.jar**.

# Использование
Запустите приложение, передав имя пользователя GitHub в качестве аргумента:

```bash
java -jar target/github-user-activity-1.0.jar {username}
```

### Пример:

```bash
java -jar target/github-user-activity-1.0.jar dynatenshi
```

### Пример вывода:

```plaintext
Username: octocat
Fetching data from: https://api.github.com/users/octocat/events
- Pushed 3 commit(s) to octocat/my-project
- Opened an issue in octocat/test-repo
- Starred google/gson
```

# Обработка исключений
<ul>
<li>Если пользователь не найден, приложение выведет: "Error 404: User not found"</li>
<li>Если от API вернулась ошибка, то приложение выведет "API error: {код ответа}"</li>
<li>Если у пользователя нет публичной активности за последние 90 дней, вы увидите сообщение: 
"No recent public activity for this user in the last 90 days"</li>
</ul>
