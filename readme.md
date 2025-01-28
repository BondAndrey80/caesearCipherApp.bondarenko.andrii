Програма шифрування тексту кодом Цезаря
Можливості програми:
    - Шифрування
    - Дешифрування
    - Брутфорс
    - CLI
    - Запуск с командного рядка

 Особливості реалізації
    - Брутфорс реалізовано методом підбору по файлу з ключами
      Тобто для взлому зашифрованого файлу користувач надає файл зі словами, які можуть бути
      у зашифрованому тексті
    - Шифрування і дешифрування можливе з одночасним використанням букв англійської і української
      мови. Тобто текст в якому буде українське слово і англійське зашифрується автоматично визначив код мови

При запуску файла без параметрів викликається CLI
java -jar "c:/My Project/target/caesarCipherApp.bondarenko.andrii.jar"

Приклади запуску файла з параметрами
 Для шифрування
 java -jar "c:/My Project/target/caesarCipherApp.bondarenko.andrii.jar" ENCRYPT "folder name/textFile1.txt" 20
 
Для дешифрування
java -jar "c:/My Project/target/caesarCipherApp.bondarenko.andrii.jar" DECRYPT "folder name/textFile1.txt" 20

Для брутфорс
java -jar "c:/My Project/target/caesarCipherApp.bondarenko.andrii.jar" BRUTE_FORCE "folder name/keyFile1.txt" 20