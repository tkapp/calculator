package jp.tkapp.calculator

import scala.BigDecimal

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import jp.tkapp.calculator.ExpressionParser

/**
 * ExpressionParserのテストクラスです。
 */
@RunWith(classOf[JUnitRunner])
class ExpressionParserTest extends Specification {

  "計算" should {

    "足し算ができる" in {
      val actual = new ExpressionParser("1 + 2 + 3").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 6
    }

    "引き算ができる" in {
      val actual = new ExpressionParser("1 - 2 - 3").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == -4
    }

    "掛け算ができる" in {
      val actual = new ExpressionParser("1 * 2 * 3").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 6
    }

    "割り算ができる" in {
      val actual = new ExpressionParser("1 / 2").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 0.5
    }

    "マイナスの掛け算ができる" in {
      val actual = new ExpressionParser("-1 * -2").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 2
    }

    "足し算掛け算の優先順位を正しく判断できる" in {
      val actual = new ExpressionParser("1 + 2 * 3").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 7
    }

    "掛け算と割り算の優先順位を正しく判断できる" in {
      val actual = new ExpressionParser("10 / 5 * 2").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 4
    }
        
    "小数点の計算ができる" in {
      val actual = new ExpressionParser("1 + 0.1 + 0.1 * 0.1").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 1.11
    }

    "括弧の計算ができる" in {
      val actual = new ExpressionParser("(1 + 2) * (3 + 4)").expr() match {
        case Left(e) => BigDecimal(-1)
        case Right(v) => v
      }
      actual.doubleValue() == 21
    }

    "0除算はエラーメッセージ" in {
      val message = new ExpressionParser("0 / 100").expr() match {
        case Left(e) => e.getMessage
        case Right(v) => ""
      }
      message == "ゼロを除算することはできません。"
    }

    "0除算はエラーメッセージ" in {
      val message = new ExpressionParser("(1 - 1) / 100").expr() match {
        case Left(e) => e.getMessage
        case Right(v) => ""
      }
      message == "ゼロを除算することはできません。"
    }

    "数式不正はエラーメッセージ" in {
      val message = new ExpressionParser("1 + + 2").expr() match {
        case Left(e) => e.getMessage
        case Right(v) => ""
      }
      message == "数式が正しくありません。"
    }

    "数式不正はエラーメッセージ" in {
      val message = new ExpressionParser("1 +").expr() match {
        case Left(e) => e.getMessage
        case Right(v) => ""
      }
      message == "数式が正しくありません。"
    }
    
    "数式不正はエラーメッセージ" in {
      val message = new ExpressionParser("a + b").expr() match {
        case Left(e) => e.getMessage
        case Right(v) => ""
      }
      message == "数式が正しくありません。"
    }
  }
}