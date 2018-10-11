package com.uron.sandbox.models

case class Asks(price: String, amount: String)

object Asks {
  def zero: Asks = apply("0", "0")
}

case class Bids(price: String, amount: String)

object Bids {
  def zero: Bids = apply("0", "0")
}

case class Depth(asks: List[Asks], bids: List[Bids])

case class DepthMessage(data: Depth, pid: Int)
