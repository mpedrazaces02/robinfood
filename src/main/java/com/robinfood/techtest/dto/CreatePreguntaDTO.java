package com.robinfood.techtest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "CreatePregunta")
public class CreatePreguntaDTO implements Serializable {

    @ApiModelProperty(value = "Titulo de la pregunta.")
    private String titulo;

    @ApiModelProperty(value = "Tipo de la pregunta.")
    private Integer tipo;

    @ApiModelProperty(value = "Id de la encuesta.")
    private Integer encuesta_id;

}
