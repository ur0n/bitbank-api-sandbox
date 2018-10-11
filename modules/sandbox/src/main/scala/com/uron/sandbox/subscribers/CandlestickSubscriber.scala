package com.uron.sandbox.subscribers

import com.pubnub.api.{Callback, Pubnub, PubnubError}
import com.uron.sandbox.models.CandlestickMessage
import com.uron.sandbox.serializers.CandlestickMessageSerializer
import org.json4s.DefaultFormats

trait CandlestickSubscriber extends BbotSubscriber[CandlestickMessage] {
  override val messageFormat = DefaultFormats + new CandlestickMessageSerializer
}

trait UsesCandlestickSubscriber {
  val candlestickSubscriber: CandlestickSubscriber
}

object CandlestickSubscriberImpl extends CandlestickSubscriber {
  def subscribe()(implicit pubnub: Pubnub): Unit = {

    val callback = new Callback {
      override def connectCallback(channel: String, message: Object): Unit =
        println("SUBSCRIBE : CONNECT on channel: " + channel + " : " + message.getClass +
          " : " + message.toString)

      override def disconnectCallback(channel: String, message: Object): Unit =
        println("SUBSCRIBE : DISCONNECT on channel: " + channel + " : " + message.getClass +
          " : " + message.toString)

      override def successCallback(channel: String, message: Object): Unit = _successCallback(channel, message)

      override def errorCallback(channel: String, pubnubError: PubnubError): Unit =
        println("SUBSCRIBE : ERROR on channel:" + channel + " : " + pubnubError)

      override def reconnectCallback(channel: String, message: Object): Unit =
        println("SUBSCRIBE : RECONNECT on channel:" + channel + " : " + message.getClass +
          " : " + message.toString)
    }
    pubnub.subscribe("candlestick_btc_jpy", callback)
  }
}

trait MixInCandlestickSubscriber {
  val candlestickSubscriber: CandlestickSubscriber = CandlestickSubscriberImpl
}

