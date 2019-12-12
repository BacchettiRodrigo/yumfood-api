package br.com.reignited.yumfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Assembler<T> {

    @Autowired
    private ModelMapper modelMapper;

//    public T toSingleton(Object source) {
//        return modelMapper.map(source, targetClass);
//    }
}
