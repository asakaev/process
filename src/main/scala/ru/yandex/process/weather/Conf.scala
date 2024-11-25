package ru.yandex.process.weather

import java.time.Duration

case class Conf(tokens: List[Int], limit: Long, interval: Duration, window: Int)
