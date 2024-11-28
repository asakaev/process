package ru.yandex.process.weather

import zio.Clock.instant
import zio.Schedule
import zio.stream.{UStream, ZPipeline, ZStream}

import java.time.Instant

def temp(w: Weather, c: Conf): UStream[Int] =
  ZStream
    .unfold("Moscow")(city => Some(w.temp(city), city))
    .schedule(Schedule.spaced(c.interval))
    .take(c.limit)

def tempN(c: Conf): UStream[Int] =
  ZStream
    .fromIterable(c.tokens)
    .flatMap(token => WeatherSimulator.make(token))
    .flatMap(w => temp(w, c))
    .forever

def avg(n: Int): ZPipeline[Any, Nothing, Int, Int] =
  ZPipeline
    .scan[Int, (Int, Int, Option[Int])](1, 0, None) { case ((w, sum, _), x) =>
      if w == n then (1, 0, Some(sum + x)) else (w + 1, sum + x, None)
    }
    .collect { case (_, _, Some(sum)) => sum / n }

def program(c: Conf): UStream[Unit] =
  LogSimulator.make.flatMap: log =>
    tempN(c).via(avg(c.window)).zip(ZStream.repeatZIO(instant)).map(log.produce)
