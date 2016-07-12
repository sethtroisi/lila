package lila.stripe

import org.joda.time.DateTime

case class Patron(
    _id: Patron.UserId,
    stripe: Option[Patron.Stripe] = none,
    payPal: Option[Patron.PayPal] = none,
    lastLevelUp: DateTime) {

  def id = _id

  def userId = _id

  def canLevelUp = lastLevelUp isBefore DateTime.now.minusDays(25)
}

object Patron {

  case class UserId(value: String) extends AnyVal

  case class Stripe(customerId: CustomerId)

  case class PayPal(
      email: Option[PayPal.Email],
      subId: Option[PayPal.SubId],
      lastCharge: DateTime) {
    def isExpired = lastCharge isBefore DateTime.now.minusMonths(1)
  }
  object PayPal {
    case class Email(value: String) extends AnyVal
    case class SubId(value: String) extends AnyVal
  }
}
