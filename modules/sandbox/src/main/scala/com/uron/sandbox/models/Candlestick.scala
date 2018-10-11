package com.uron.sandbox.models

case class OHLCV(open: String, height: String, low: String, close: String, volume: String, unixTime: Long)

object OHLCV {
  def empty: OHLCV = apply("0", "0", "0", "0", "0", 0)
}

case class Candlestick(ohlcv: List[OHLCV], `type`: String)

case class CandlestickMessage(data: List[Candlestick], pid: Int)
