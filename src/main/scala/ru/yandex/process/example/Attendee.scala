package ru.yandex.process.example

import zio.stream.{UStream, ZStream}

case class Slide()
case class Brain()

def learn(b: Brain, s: Slide): Brain = ???

def insight(state: (Brain, Boolean)): UStream[String] =
  state match
    case (_, true) => ZStream.succeed("💡")
    case _         => ZStream.empty

def attendee(slides: UStream[Slide], rnd: UStream[Boolean]): UStream[String] =
  slides
    .scan(Brain())(learn) // UStream[Brain]
    .zip(rnd)             // UStream[(Brain, Boolean)]
    .flatMap(insight)     // UStream[String]
