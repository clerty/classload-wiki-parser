# Загрузка классов #

## Описание
Консольная программа, которая получает ключевое слово для поиска на [Wikipedia](https://en.wikipedia.org) (как аргументы запуска или через stdin), отправляет запрос с помощью [Apache HTTP Client](https://hc.apache.org/httpcomponents-client-ga/tutorial/html/index.html),
достает из результатов текст с помощью [Json Parser'а]((https://github.com/google/gson)) в формате Wiki Markdown и на основе динамически загружаемых jar-"плагинов" с помощью URLCLassLoader на выходе формирует отчет и записывает результат в файл.

Пример запроса к Wikipedia: https://en.wikipedia.org/w/api.php?action=parse&page=Hypertext_Transfer_Protocol&format=json&prop=wikitext&formatversion=2.
   
Для разбиения текста на слова используется RegExp `(\b[a-zA-Z][a-zA-Z.\-0-9]*\b)` ([Пояснение](https://regexper.com/#%28%5Cb%5Ba-zA-Z%5D%5Ba-zA-Z.%5C-0-9%5D*%5Cb%29)).

В проекте созданы подпроекты для двух плагинов:
  - [Counter Plugin](/counter-plugin) – подсчет количество строк, слов, букв и символов в словах ('.', '-', 0..9). На вход плагин получает текст,
на выходе формирует _только_ строку в формате `<lines>;<words>;<letters>`.
  - [Frequency Dictionary Plugin](/frequency-dictionary-plugin) – частотный словарь, подсчитывает сколько раз встречается каждое слово.
Словоформы не учитывать, т.е. слова _аргумент_ и _аргументы_ считаются двумя _разными_ словами. Союзы, предлоги и прочие части речи длиной в одну букву учитываются.

Результат обработки текста плагином записывается в папку [results](/results) в формате request-name/results-<plugin-name>.txt.

Для автоматизации проверки реализованы интеграционные тесты, выполняемые на GitHub через Actions. Процесс тестирования состоит из 2х шагов:
- прогон тестов и сборка (сначала плагины, потом `main-module`);
- запуск bash-скрипта, который вызывает `java -jar main-module.jar` и на вход направляет имена статей. После прогона выполнения скрипт сравнивает
результаты в папке `$rootDir/results` и образцовые результаты в папке `integration-test/results`.

## Сборка приложения 
```shell script
# загружает gradle wrapper 6.6
./gradlew wrapper

# сборка проекта, прогон тестов и копирование плагинов в /plugins
./gradlew clean build 

# запуск приложения, важно что это нужно делать из $rootDir, т.к. пути папок plugins/ и results/ строятся от корня
java -jar main-module/build/libs/main-module.jar  
```



