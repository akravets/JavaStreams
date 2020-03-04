package com.akravets.streams;

import java.util.function.Function;

public class LambdaExample {
    private String lambdaVar = "clasInstanceVariable";
    /**
     *
     * @param input
     * @return
     */
    public String lambda(String input){
        return myFunction().apply(input);
    }

    /**
     * This shows the scope of lambda functions. In implementation we call <code>this.lambdaVar</code>, we have this
     * variable defined in myFunction() and in surrounding LambdaExample class. Because of lambda's scoping when
     * <code>this</code> is called, it references not <code>myFunctions</code>'s <code>lambdaVar</code>, but
     * Lambda's instance.
     * @return
     */
    public Function<String, String> myFunction(){
        String lambdaVar = "lambdaInstanceVariable";
        return (input) -> this.lambdaVar;
    }
}
