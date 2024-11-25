package ru.yandex.process.weather

import zio.*

object Main extends ZIOAppDefault:
  def run: ZIO[ZIOAppArgs & Scope, Any, Any] =
    program(Conf(List(1, 2), 3, 1.second, 2)).take(4).runDrain
