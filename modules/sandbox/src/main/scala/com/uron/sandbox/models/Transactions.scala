package com.uron.sandbox.models

case class Transaction(transactionId: Int, side: String, amount: String, price: String, executedAt: Long)

case class Transactions(transactions: List[Transaction])

case class TransactionsMessage(data: Transactions, pid: Int)
