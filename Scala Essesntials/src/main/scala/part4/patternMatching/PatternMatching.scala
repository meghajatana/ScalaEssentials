package part4.patternMatching

object PatternMatching extends App {

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr)  extends Expr

  def prettyExpression(expr:Expr): String = {
    expr match {
    case Number(num1) => s"$num1"
    //case Sum(expr1:Number, expr2: Number) => s"${prettyExpression(expr1)} + ${prettyExpression(expr2)}"
    case Prod(expr1: Sum, expr2)  => s"(${prettyExpression(expr1)}) * ${prettyExpression(expr2)}"
    case Prod(expr1, expr2: Sum)  => s"${prettyExpression(expr1)} * (${prettyExpression(expr2)})"
    case Prod(expr1, expr2) => s"${prettyExpression(expr1)} * ${prettyExpression(expr2)}"
    case Sum(expr1, expr2) => s"${prettyExpression(expr1)} + ${prettyExpression(expr2)}"

    }
  }
   println(prettyExpression(Sum(Number(1),Number(2))))
  println(prettyExpression(Prod(Sum(Number(1), Number(2)), Number(5))))
  println(prettyExpression(Prod(Prod(Number(1), Number(2)), Number(5))))
  println(prettyExpression(Sum(Prod(Number(1), Number(2)), Number(5))))
  println(prettyExpression(Prod(Number(1), Sum(Number(2), Number(5)))))
}
