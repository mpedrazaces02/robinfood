package com.robinfood.techtest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@ApiModel(value = "CreatePreguntaCompleta")
public class CreatePreguntaCompletaDTO implements Serializable {

    @ApiModelProperty(value = "Titulo de la pregunta.")
    private String titulo;

    @ApiModelProperty(value = "Tipo de la pregunta.")
    private Integer tipo;

    @ApiModelProperty()
    private CreateEncuestaCompletaDTO encuesta;

    @ApiModelProperty(value = "Opciones de la pregunta.")
    private List<CreateOpcionPreguntaCompletaDTO> opciones;

}
