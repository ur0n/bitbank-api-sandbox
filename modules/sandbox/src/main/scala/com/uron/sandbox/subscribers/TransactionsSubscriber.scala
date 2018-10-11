package com.uron.sandbox.subscribers

import com.pubnub.api.{Callback, Pubnub, PubnubError}
import com.uron.sandbox.models.TransactionsMessage
import com.uron.sandbox.serializers.TransactionsMessageSerializer
import org.json4s.{DefaultFormats, Formats}

trait TransactionsSubscriber extends BbotSubscriber[TransactionsMessage] {
  override val messageFormat: Formats = DefaultFormats + new TransactionsMessageSerializer
}

trait UsesTransactionsSubscriber {
  val transactionsSubscriber: TransactionsSubscriber
}

object TransactionsSubscriberImpl extends TransactionsSubscriber {
  def subscribe()(implicit pubnub: Pubnub): Unit = {
    implicit val formats = DefaultFormats

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
    pubnub.subscribe("transactions_btc_jpy", callback)
  }
}

trait MixInTransactionsSubscriber {
  val transactionsSubscriber: TransactionsSubscriber = TransactionsSubscriberImpl
}
