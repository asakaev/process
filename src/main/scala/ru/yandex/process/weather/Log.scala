package ru.yandex.process.weather

import zio.Console.printLine
import zio.stream.{UStream, ZStream}

import java.time.Instant

trait Log[A]:
  def produce(value: A): Unit

object LogSimulator:

  def log[A]: Log[A] = new Log[A]:
    def produce(value: A): Unit = println(s"  produce $value")

  val make: UStream[Log[(Int, Instant)]] =
    val a = printLine("Entering the Log scope").as(log[(Int, Instant)])
    val r = printLine("Leaving the Log scope")
    ZStream.acquireReleaseWith(a.orDie)(_ => r.orDie)
