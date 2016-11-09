package jp.tkapp.caluculator

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
  def calculate(formula : String) : Int = {
    //
    if (formula.indexOf("(") >= 0) {
      0
    } else {
      1
    }
    
    //
    1
  }
}