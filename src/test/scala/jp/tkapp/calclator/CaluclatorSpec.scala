package jp.tkapp.calclator

import org.specs2.mutable.Specification
import jp.tkapp.calculator.Calculator

import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

/**
 * Calclatorのテストクラスです。
 */
@RunWith(classOf[JUnitRunner])
class CaluclatorSpec extends Specification {

  "計算" should {
    "2項の足し算ができる" in {
      Calculator.calculate("1 + 1") == 2
    }
  }
}