package ru.yandex.process.example

import ru.yandex.process.weather.Weather
import zio.stream.{UStream, ZStream}
import zio.{Schedule, durationInt}

import java.time.Duration

val clock: UStream[Unit] = ZStream.tick(1.second)

def temp(w: Weather, interval: Duration): UStream[Int] =
  ZStream
    .unfold("Moscow")(city => Some((w.temp(city), city)))
    .schedule(Schedule.spaced(interval))
