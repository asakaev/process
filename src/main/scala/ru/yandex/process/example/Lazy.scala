package ru.yandex.process.example

import ru.yandex.process.weather.{Log, Weather}

import java.time.Instant
import scala.util.Random

def produce(
    log: Log[(Int, Instant)],
    avgs: LazyList[Int],
    ts: LazyList[Instant]
): LazyList[Unit] =
  avgs
    .zip(ts)
    .map(log.produce)

def avg(xs: LazyList[Int]): Int =
  xs.foldLeft(0, 0) { case ((sum, n), x) =>
    (sum + x, n + 1)
  } match
    case (sum, n) =>
      if n == 0 then 0
      else sum / n

val nats: LazyList[Int] = LazyList.iterate(0)(_ + 1)

val nats3: List[Int] = nats.take(3).toList
// [0, 1, 2]

def windows(xs: LazyList[Int], n: Int): LazyList[LazyList[Int]] =
  xs
    .grouped(n)
    .to(LazyList)

def avgN(xs: LazyList[Int], n: Int): LazyList[Int] =
  xs
    .scanLeft(1, 0, Option.empty[Int]) { case ((w, sum, _), x) =>
      if w == n then (1, 0, Some(sum + x))
      else (w + 1, sum + x, None)
    }
    .collect { case (_, _, Some(sum)) => sum / n }

// n = 3
// [0, 1, 2, 3, 4, 5, 6, 7, 8] -> [1, 4, 7]

opaque type Transducer[A, B] =
  LazyList[A] => LazyList[B]

// map, filter, scan...

def temp(w: Weather): LazyList[Int] =
  LazyList.unfold("Moscow"): city =>
    Some(w.temp(city), city)

val coin: LazyList[Boolean] =
  LazyList.unfold(Random()): rnd =>
    Some(rnd.nextBoolean, rnd)
