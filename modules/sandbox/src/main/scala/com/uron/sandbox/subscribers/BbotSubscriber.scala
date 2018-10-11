package com.uron.sandbox.subscribers

import com.pubnub.api.Pubnub
import org.json.JSONObject
import org.json4s._
import org.json4s.jackson.JsonMethods._

trait BbotSubscriber[M] {
  val messageFormat: Formats = DefaultFormats

  def subscribe()(implicit pubnub: Pubnub): Unit

  def _successCallback(channel: String, message: Object)(implicit m: Manifest[M]): Unit = {
    implicit val formats = messageFormat
    println(message)
    val json = parse(message.asInstanceOf[JSONObject].toString)
    println(json.extract[M])
  }
}
