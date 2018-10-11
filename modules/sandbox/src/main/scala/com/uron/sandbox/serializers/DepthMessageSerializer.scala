package com.uron.sandbox.serializers

import com.uron.sandbox.models._
import org.json4s.{CustomSerializer, JObject, _}

class DepthMessageSerializer extends CustomSerializer[DepthMessage](implicit formats => ( {
  case jsonObj: JObject =>
    val data = jsonObj \ "data"
    val pid = (jsonObj \ "pid").extract[Int]

    val asks: List[Asks] = (data \ "asks").children.map {
      case JArray(JString(price) :: JString(amount) :: Nil) =>
        Asks(price, amount)
      case _ => Asks.zero
    }

    val bids: List[Bids] = (data \ "bids").children.map {
      case JArray(JString(price) :: JString(amount) :: Nil) =>
        Bids(price, amount)
      case _ => Bids.zero
    }

    DepthMessage(Depth(asks, bids), pid)
}, {
  case depth: DepthMessage => JObject()
}
))
