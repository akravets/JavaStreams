# JavaStreams

## Predicate
Predicate: A predicate is used to apply a filter to a collection of objects. Predicate is a functional interface and supports lambda 
expressions. Separating predicates that way makes them reusable, and you can achieve separation of the business layer and domain layer.

```java
Predicate p = new Predicate() {
   @Overrid
   public boolean test(Object o) {
        return ((String)o).length() == 3;
   }
};
Stream.of("electricity", "duck", "birds", "cat").
    filter(p).collect(Collectors.toSet()); // result: cat
```

## BiFunction
BiFunction is a functional interface that can be used as the assignment target for a lambda expression or method reference. 
It represents an operation upon two operands of the same type, producing a result of the same type as the operands.

BiFunction can be used to chain two Function together.

```java
BinaryOperator<Integer> add = (o, o2) -> {
   System.out.println(o+o2);
   return o + o2;
};

// The reason multiply is Function and not another BinaryOperator is because andThen() expects a Function as parameter
Function<Integer, Integer> multiply = (o) -> o * 2;

BiFunction<Integer,Integer,Integer> addAndMultiply = add.andThen(multiply);

Integer apply = addAndMultiply.apply(2, 3); // 10
```

