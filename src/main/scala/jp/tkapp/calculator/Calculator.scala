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
  def calculate(formula: String): Double = {
    //
    if (formula.indexOf(" ") >= 0) {
      calculate(formula.replaceAll(" ", ""))
    } else if (formula.indexOf("(") >= 0) {
      0
    } else {
      //
      val items = formula.split("\\+")
      items(0).toDouble + items(1).toDouble
    }
  }
}