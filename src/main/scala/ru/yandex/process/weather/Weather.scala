package ru.yandex.process.weather

import zio.Console.printLine
import zio.stream.{UStream, ZStream}

import java.util.Random

trait Weather:
  def temp(city: String): Int

object WeatherSimulator:
  val rnd = Random()
  val l   = -4
  val r   = 2

  val weather: Weather = new Weather:
    def temp(city: String): Int =
      val t = rnd.nextInt(r - l) + l
      println("    temp " + t)
      t

  def make(token: Int): UStream[Weather] =
    val a = printLine(s"  Entering the Weather [$token] scope").as(weather)
    val r = printLine(s"  Leaving the Weather [$token] scope")
    ZStream.acquireReleaseWith(a.orDie)(_ => r.orDie)
