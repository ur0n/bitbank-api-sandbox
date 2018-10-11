package com.uron.sandbox.serializers

import com.uron.sandbox.models._
import org.json4s.{CustomSerializer, JObject, _}

class TransactionsMessageSerializer extends CustomSerializer[TransactionsMessage](implicit formats => ( {
  case jsonObj: JObject =>
    val data = jsonObj \ "data"
    val pid = (jsonObj \ "pid").extract[Int]

    val transactions: Transactions = data.camelizeKeys.extract[Transactions]

    TransactionsMessage(transactions, pid)
}, {
  case transactions: TransactionsMessage => JObject()
}
))
