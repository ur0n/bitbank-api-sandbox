package com.uron.sandbox.models

//{"data":{"high":"739900","vol":"2883.3344","last":"698427","low":"690027","sell":"698066","buy":"698025","timestamp":1539243471605},"pid":436588182}
case class Ticker(high: String, vol: String, last: String, low: String, sell: String, buy: String, timestamp: Long)

case class TickerMessage(data: Ticker, pid: Int)
