package br.com.reignited.yumfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public abstract class Assembler<T,S> {

    @Autowired
    protected ModelMapper mapper;

    public abstract T toModel(S source);

    public abstract List<T> toCollectionModel(Collection<S> source);
}
