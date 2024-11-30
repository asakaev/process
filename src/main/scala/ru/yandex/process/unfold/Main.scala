package ru.yandex.process.unfold

import zio.*
import zio.Console.printLine
import zio.stream.ZStream

object Main extends ZIOAppDefault:
  def run: ZIO[ZIOAppArgs & Scope, Any, Any] =
    ZStream
      .apply(
        evens,
        fib,
        factorial,
        collatz(3),
        constant("✨"),
        alternate,
        powersOfTen,
        countdown(10),
        digits(12345),
        repeatN(2, "👋"),
        random(0L)
      )
      .mapZIO(_.take(8).runCollect)
      .tap(printLine(_))
      .runDrain
