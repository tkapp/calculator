package jp.tkapp.calculator

import scala.Left
import scala.Right
import scala.math.BigDecimal
import scala.util.control.Breaks

/**
 * 計算式のパーサーです。
 */
class ExpressionParser(expression: String) {

  /** トークナイザー */
  val tokenizer = new ExpressionTokenizer(expression)

  /**
   * 計算式を評価します。
   */
  def expr(): Either[Throwable, BigDecimal] = {
    //
    var number: BigDecimal = term() match {
      case Left(e) => return Left(e)
      case Right(n) => n
    }

    val break = Breaks
    break.breakable {
      while (true) {
        val item = tokenizer.peek()
        item match {
          case "+" => {
            tokenizer.next()
            val nextNumber = term() match {
              case Left(e) => return Left(e)
              case Right(n) => n
            }

            number += nextNumber
          }
          case "-" => {
            tokenizer.next()
            val nextNumber = term() match {
              case Left(e) => return Left(e)
              case Right(n) => n
            }

            number -= nextNumber
          }
          case _ => break.break()
        }
      }
    }

    Right(number)
  }

  /**
   * タームを評価します。
   */
  def term(): Either[Throwable, BigDecimal] = {
    //
    var number: BigDecimal = factor() match {
      case Left(e) => return Left(e)
      case Right(n) => n
    }

    val break = Breaks
    break.breakable {
      while (true) {
        val item = tokenizer.peek()
        item match {
          case "*" => {
            tokenizer.next()
            val nextNumber = factor() match {
              case Left(e) => return Left(e)
              case Right(n) => n
            }

            number *= nextNumber
          }
          case "/" => {
            if (number == 0) {
              return Left(new Exception("ゼロを除算することはできません。"))
            }

            tokenizer.next()
            val nextNumber = factor() match {
              case Left(e) => return Left(e)
              case Right(n) => n
            }

            number /= nextNumber
          }
          case _ => break.break();
        }
      }
    }

    Right(number)
  }

  /**
   * 要素を評価します。
   */
  def factor(): Either[Throwable, BigDecimal] = {
    //
    val item = tokenizer.next()
    item match {
      case "(" => {
        //
        val number: BigDecimal = expr() match {
          case Left(e) => return Left(e)
          case Right(n) => n
        }

        val item = tokenizer.next() // ")" をとばす
        if (item != ")") {
          Left(new Exception("括弧の数が合いません。"))
        }

        Right(number)
      }
      case _ => {
        try {
          Right(BigDecimal(item))
        } catch {
          case t: NumberFormatException => Left(new Exception("数式が正しくありません。"))
        }
      }
    }
  }
}