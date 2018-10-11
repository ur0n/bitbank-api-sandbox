
# bitbank-api-sandbox

## Run
```
$ sbt

$ sbt> sandbox/run
.... print bitbank realtime data
```
## Memo

* [BitbankAPI](https://docs.bitbank.cc/#/)

Subscribed data of realtime API using [Pubnub](https://www.pubnub.com/) is very gross..... <br>
There are data that String and Long are mixed in one array.

ex) Candistick Data
```
{
   "data":{
      "candlestick":[
         {
            "ohlcv":[
               [
                  "700955",
                  "700975",
                  "700900",
                  "700909",
                  "0.4378",
                  1539278460000
               ]
            ],
            "type":"1min"
         },
         .
         .
         .
      ]
      "timestamp":1539278490208
   },
   "pid":437116764
}
```
So it was difficult to parse by the usual way.<br>
Therefore, I created CustomSerialize class referencing the [json4s#Serializing non-supported types](https://github.com/json4s/json4s#serializing-non-supported-types).

This repository is knowledge of above problem solution.
