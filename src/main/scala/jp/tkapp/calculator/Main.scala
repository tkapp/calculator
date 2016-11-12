
package jp.tkapp.calculator

/**
 * 実行クラスです。
 */
object Main {

  /**
   * 実行します。
   *
   * @param args 実行時引数
   */
  def main(args: Array[String]): Unit = {
    //
    if (args.size > 0) {
      val parser = new ExpressionParser(args(0))
      caluculate(parser)
    } else {
      var expression = ""
      while (expression != "exit" && expression != "\\q") {
        expression = readLine()
        val parser = new ExpressionParser(expression)
        caluculate(parser)
      }
    }
  }

  /**
   * 計算結果を表示します。
   */
  def caluculate(parser: jp.tkapp.calculator.ExpressionParser): Unit = {
    val result = parser.expr()
    result match {
      case Left(e) => println(e.getMessage())
      case Right(v) => println(v)
    }
  }
}