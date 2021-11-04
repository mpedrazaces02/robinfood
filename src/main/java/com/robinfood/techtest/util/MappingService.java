package com.robinfood.techtest.util;

/*
 * @author Miguel Pedraza Céspedes
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MappingService {

    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;

    private ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setAmbiguityIgnored(Boolean.TRUE)
                    .setSkipNullEnabled(Boolean.TRUE);
        }

        return modelMapper;
    }

    private ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper;
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return getMapper().map(source, destinationType);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> getMapper().map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <T> T jsonToObject(String content, Class<T> targetClass) throws Exception {
        return getObjectMapper().readValue(content, targetClass);
    }

    public String objectToJson(Object obj) throws Exception {
        return getObjectMapper().writeValueAsString(obj);
    }

    public JsonNode jsonToObject(String content) throws Exception {
        return getObjectMapper().readTree(content);
    }
}
