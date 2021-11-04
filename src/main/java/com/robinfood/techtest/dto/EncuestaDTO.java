package com.robinfood.techtest.dto;

import com.robinfood.techtest.entity.Pregunta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@ApiModel(value = "EncuestaDTO")
public class EncuestaDTO implements Serializable {

    @ApiModelProperty(value = "Id de la encuesta. ")
    private Integer id;

    @ApiModelProperty(value = "Titulo de la encuesta.")
    private String titulo;

    @ApiModelProperty(value = "Fecha de inicio de la encuesta.")
    private Date fecha_inicio;

    @ApiModelProperty(value = "Fecha final de la encuesta.")
    private Date fecha_fin;

    @ApiModelProperty(value = "Estado de la encuesta.")
    private Integer estado;

    @ApiModelProperty(value = "Listado de las preguntas.")
    private Set<PreguntaDTO> preguntas;
}
