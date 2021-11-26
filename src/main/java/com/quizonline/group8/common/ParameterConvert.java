package com.quizonline.group8.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParameterConvert {
    public static <T,U> U stringToNumber(List<T> values, Function<T,U> function){
        return values.stream().map(function).collect(Collectors.toList()).get(0);
    }
}
