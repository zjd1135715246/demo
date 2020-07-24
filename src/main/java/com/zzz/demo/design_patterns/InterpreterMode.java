package com.zzz.demo.design_patterns;

import java.util.HashMap;

/**
 * 解释器模式
 * @author zhangjiadong
 * @date 2020/7/22
 */
public class InterpreterMode {
}

/**
 * 抽象类表达式
 */
abstract class Expression{
    abstract int interpreter(HashMap<String,Integer> var);
}

/**
 * 变量的解释器
 */
class VarExpression extends Expression{

    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}

/**
 * 运算符解释器
 */
class SymbolExpression extends Expression{

    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}

class AddExpression extends SymbolExpression{
    public AddExpression(Expression left,Expression right) {
        super(left,right);
    }

    public int interpreter(HashMap<String, Integer> var){
        return super.left.interpreter(var)+super.right.interpreter(var);
    }
}

class SubExpression extends SymbolExpression{
    public SubExpression(Expression left,Expression right) {
        super(left,right);
    }

    public int interpreter(HashMap<String, Integer> var){
        return super.left.interpreter(var)-super.right.interpreter(var);
    }
}