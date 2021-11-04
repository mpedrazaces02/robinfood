package com.robinfood.techtest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "CreateEncuesta")
public class CreateEncuestaDTO implements Serializable {

    @ApiModelProperty(value = "Titulo de la encuesta.")
    private String titulo;

    @ApiModelProperty(value = "Fecha de inicio de la encuesta.")
    private Date fecha_inicio;

    @ApiModelProperty(value = "Fecha final de la encuesta.")
    private Date fecha_fin;

    @ApiModelProperty(value = "Estado de la encuesta.")
    private Integer estado;
}
