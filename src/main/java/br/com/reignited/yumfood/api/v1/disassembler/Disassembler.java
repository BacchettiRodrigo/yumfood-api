package br.com.reignited.yumfood.api.v1.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Disassembler<T, S> {

    @Autowired
    protected ModelMapper mapper;

    public abstract T toDomainModel(S source);

    public abstract void copyToDomainObject(S source, T target);
}
