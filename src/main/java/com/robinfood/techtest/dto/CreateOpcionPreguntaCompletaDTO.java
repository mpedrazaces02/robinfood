package com.robinfood.techtest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "CreateOpcionPregunta")
public class CreateOpcionPreguntaCompletaDTO implements Serializable {

    @ApiModelProperty(value = "Opcion de la pregunta.")
    private String texto;

    @ApiModelProperty(value = "Ejemplo de la opcion de la pregunta.")
    private String ejemplo;

    @ApiModelProperty()
    private CreatePreguntaCompletaDTO pregunta;

}
