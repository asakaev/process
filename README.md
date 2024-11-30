## Процесс

[Доклад на F[Scala] 2024](https://yandex.ru/project/verticals/fscala2024).

### Моделирование процессов на Scala
Дополнение к докладу: примеры кода и материалы, показывающие, как использовать стримы на Scala для моделирования процессов, управления временем и состоянием.

### Задача
* Хранить данные по средней температуре за каждый час (41 измерений в час)
* Ограничение на количество вызовов к сервису погоды — 1000 запросов в сутки (в среднем запрос в 87 секунды)

### Коротко о погоде
* Один Log на время жизни программы
* Два Weather, которые меняются каждые три вызова
* Публикация в Log средних по двум значениям температуры
* Остановка после четырёх публикаций

```
limit=3, window=2, interval=1s, take=4

Entering the Log scope
  Entering the Weather [1] scope
    temp -1
    temp 1
  produce (0,2024-11-24T16:16:49.119860Z)
    temp -1
  Leaving the Weather [1] scope
  Entering the Weather [2] scope
    temp -4
  produce (-2,2024-11-24T16:16:51.164774Z)
    temp -3
    temp -1
  produce (-2,2024-11-24T16:16:53.196104Z)
  Leaving the Weather [2] scope
  Entering the Weather [1] scope
    temp 1
    temp 1
  produce (1,2024-11-24T16:16:55.205999Z)
  Leaving the Weather [1] scope
Leaving the Log scope
```

### Почитать
* [Folds and unfolds all around us](http://conal.net/talks/folds-and-unfolds.pdf). Conal Elliott.
* [Origami programming](https://disk.yandex.ru/i/pX9tnPkAkPmXjA). Jeremy Gibbons.
* [All Things Flow: Unfolding the History of Streams (extended abstract)](https://okmij.org/ftp/Computation/streams-hapoc2021.pdf). Aggelos Biboudis‚ Jeremy Gibbons and Oleg Kiselyov.
* [All Things Flow: Unfolding the History of Streams (slides)](http://biboudis.github.io/papers/streams-hapoc2021-slides.pdf). Aggelos Biboudis‚ Jeremy Gibbons and Oleg Kiselyov.
* [Machines](https://disk.yandex.ru/i/xEKEJZ5xJAsx2w). Rúnar Óli Bjarnason.
* [Iteratees](https://okmij.org/ftp/Haskell/Iteratee/describe.pdf). Oleg Kiselyov.
* [Streams and Incremental Processing](https://okmij.org/ftp/Streams.html). Oleg Kiselyov.
* [When is a function a fold or an unfold?](https://disk.yandex.ru/i/NTUAIZfuo8tI0Q). Jeremy Gibbons and Graham Hutton.
* [Functional Programming with Bananas, Lenses, Envelopes and Barbed Wire](https://maartenfokkinga.github.io/utwente/mmf91m.pdf). Erik Meijer, Maarten Fokkinga and Ross Paterson.

### Посмотреть
* [Функциональные стримы на Joker 2024](https://vkvideo.ru/video-796_456240559)
* [DDDamn good! на Joker 2021](https://vkvideo.ru/video-796_456240246)

### Благодарности
* [Алексей Шуксто](https://github.com/seigert)
* [Роман Малыгин](https://github.com/splusminusx)
