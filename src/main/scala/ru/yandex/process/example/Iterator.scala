package ru.yandex.process.example

trait Iterator[A]:
  def hasNext: Boolean
  def next(): A
