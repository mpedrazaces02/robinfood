package com.robinfood.techtest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "CreateEncuestaCompleta")
public class CreateEncuestaCompletaDTO implements Serializable {

    @ApiModelProperty(value = "Titulo de la encuesta.")
    private String titulo;

    @ApiModelProperty(value = "Fecha de inicio de la encuesta.")
    private Date fecha_inicio;

    @ApiModelProperty(value = "Fecha final de la encuesta.")
    private Date fecha_fin;

    @ApiModelProperty(value = "Fecha final de la encuesta.")
    private List<CreatePreguntaCompletaDTO> preguntas;

    @ApiModelProperty(value = "Estado de la encuesta.")
    private Integer estado;

}
