package jp.tkapp.calculator

import scala.util.control.Breaks

/**
 * 計算式を分解します。
 */
class ExpressionTokenizer(_expression: String) {

  /** 計算式 */
  protected val expression = _expression.replaceAll(" ", "")

  /** ポジション */
  private var pos = 0

  /** 次のポジション */
  private var nextPos = 0

  /** 次の値 */
  private var nextValue = ""

  /** 直前のトークンがどちらだったか operator/number/paren */
  private var beforeToken = "operator"

  /** 演算子 */
  val operator = "+-*/"

  /** 括弧 */
  val paren = "()"

  /**
   * 　次のトークンが存在するか確認します。
   *
   * @return トークンが存在する場合はtrue、存在しない場合はfalse
   */
  def hasNext(): Boolean = {
    //
    if (expression.length() > pos) true else false
  }

  /**
   * 次のトークンを取得します。
   *
   * @return トークン
   */
  def peek(): String = {
    if (pos != nextPos) {
      return nextValue
    }
    
    nextValue = ""
    
    //
    val break = new Breaks
    break.breakable {
      for (i <- pos to expression.length() - 1) {
        //
        val char = expression.charAt(i)

        //
        if (operator.contains(char) || paren.contains(char)) {
          // 数値の場合に記号が来たら処理終了
          if (nextValue.length() > 0) {
            beforeToken = "number"
            break.break();
          }

          // 前のトークンが演算子で、現在が-の場合は、負の整数として処理を継続する。
          if (beforeToken != "operator" || char != '-') {
            // 演算子または括弧の場合は処理終了
            beforeToken = if (paren.contains(char)) "paren" else "operator"
            nextValue += char
            nextPos += 1
            break.break();
          }
        }

        nextValue += char

        nextPos += 1
      }
    }

    nextValue
  }
  
  /**
   * 
   */
  def next() : String = {
    //
    peek()
    pos = nextPos
    nextValue
  }
}