package jp.tkapp.calculator

/**
 * 計算オブジェクトです。
 */
object Calculator {

  /**
   * 計算を行います。
   *
   * @param formula 計算式
   * @return 計算結果
   */
  def calculate(expression: String): Double = {
    //
    if (expression.indexOf(" ") >= 0) {
      calculate(expression.replaceAll(" ", ""))
    } else if (expression.indexOf("(") >= 0) {
      0
    } else {
      //
      while (true) {
        //
        val items = expression.split("\\+")
        items(0).toDouble + items(1).toDouble
      }

      1
    }
  }
}