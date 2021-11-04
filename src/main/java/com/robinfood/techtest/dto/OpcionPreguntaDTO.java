package com.robinfood.techtest.dto;

import com.robinfood.techtest.entity.Pregunta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "OpcionPregunta")
public class OpcionPreguntaDTO implements Serializable {

    @ApiModelProperty()
    private Integer id;

    @ApiModelProperty()
    private String texto;

    @ApiModelProperty()
    private String ejemplo;

}
