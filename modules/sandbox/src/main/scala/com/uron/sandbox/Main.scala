package com.uron.sandbox

import com.pubnub.api.Pubnub
import com.typesafe.config.{Config, ConfigFactory}
import com.uron.sandbox.subscribers._

object Main extends UsesCandlestickSubscriber with UsesTickerSubscriber with UsesDepthSubscriber with UsesTransactionsSubscriber
  with MixInCandlestickSubscriber with MixInTickerSubscriber with MixInDepthSubscriber with MixInTransactionsSubscriber {

  implicit class RichConfig(val underlying: Config) extends AnyVal {
    def getOptionalString(path: String): Option[String] = if (underlying.hasPath(path)) {
      val string = underlying.getString(path)
      if (string.isEmpty) {
        None
      } else {
        Some(underlying.getString(path))
      }
    } else {
      None
    }
  }

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()

    val mayBePubnub = for {
      mayBePublishKey <- config.getOptionalString("dev.publish_key")
      mayBeSubscribeKey <- config.getOptionalString("dev.subscribe_key")
    } yield new Pubnub(mayBePublishKey, mayBeSubscribeKey)

    mayBePubnub match {
      case Some(p) =>
        implicit val pubnub: Pubnub = p
        candlestickSubscriber.subscribe()
        tickerSubscriber.subscribe()
        depthSubscriber.subscribe()
        transactionsSubscriber.subscribe()
      case None => println("Please Set BITBANK_PUBLISH_KEY and BITBANK_SUBSCRIBE_KEY")
    }
  }
}
