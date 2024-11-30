package ru.yandex.process.unfold

import zio.stream.{UStream, ZStream}

import java.util.Random

val evens: UStream[Int] =
  ZStream.unfold(0)(s => Some(s, s + 2))

val fib: UStream[Int] =
  ZStream.unfold(0, 1)((a, b) => Some(a, (b, a + b)))

val factorial: UStream[Int] =
  ZStream.unfold(1, 1)((n, acc) => Some(acc, (n + 1, acc * (n + 1))))

def collatz(num: Int): UStream[Int] =
  ZStream.unfold(num): n =>
    if n == 1 then None
    else Some(n, if n % 2 == 0 then n / 2 else 3 * n + 1)

def constant[A](a: A): UStream[A] =
  ZStream.unfold(())(s => Some(a, s))

val alternate: UStream[Boolean] =
  ZStream.unfold(true)(x => Some(x, !x))

val powersOfTen: UStream[Int] =
  ZStream.unfold(1)(x => Some(x, x * 10))

def digits(n: Int): UStream[Int] =
  ZStream.unfold(n): x =>
    if x == 0 then None
    else Some(x % 10, x / 10)

def countdown(n: Int): UStream[Int] =
  ZStream.unfold(n): s =>
    if s == 0 then None
    else Some(s, s - 1)

def repeatN[A](n: Int, a: A): UStream[A] =
  ZStream.unfold(n): i =>
    if i == 0 then None
    else Some(a, i - 1)

def random(seed: Long): UStream[Int] =
  ZStream.unfold(Random(seed))(r => Some(r.nextInt, r))
