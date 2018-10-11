package com.uron.sandbox.serializers

import com.uron.sandbox.models.{Candlestick, CandlestickMessage, OHLCV}
import org.json4s.{CustomSerializer, JObject, _}

class CandlestickMessageSerializer extends CustomSerializer[CandlestickMessage](implicit formats => ( {
  case jsonObj: JObject =>
    val data = jsonObj \ "data"
    val pid = (jsonObj \ "pid").extract[Int]

    val candlestickMessage = (data \ "candlestick").children.map(candlestick => {
      val ohlcvs = (candlestick \ "ohlcv").children.map {
        case JArray(JString(o) :: JString(h) :: JString(l) :: JString(c) :: JString(v) :: JInt(unixTime) :: Nil) =>
          OHLCV(o, h, l, c, v, unixTime.longValue())
        case _ => OHLCV.empty
      }
      val `type` = (candlestick \ "type").extractOrElse[String]("")
      Candlestick(ohlcvs, `type`)
    })

    CandlestickMessage(candlestickMessage, pid)
}, {
  case candlesticMessage: CandlestickMessage => JObject()
}
))
