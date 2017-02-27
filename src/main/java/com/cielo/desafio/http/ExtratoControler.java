package com.cielo.desafio.http;

import com.cielo.desafio.http.dto.ExtratoDTO;
import com.cielo.desafio.http.dto.PeriodoDTO;
import com.cielo.desafio.usecase.ExtratoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class ExtratoControler {

    private final ExtratoService extratoService;


    @ApiOperation(value = "extrato", nickname = "Extrato de um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ExtratoDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/extrato/{cnpj}")
    public ResponseEntity<List<ExtratoDTO>> extrato(@ApiParam @PathVariable String cnpj, @RequestBody @Valid final PeriodoDTO periodo)
            throws URISyntaxException {
        log.debug("REST request to get extrato");

        List<ExtratoDTO> content = extratoService.findByCnpjAndDataLancamentoBetween(
                cnpj,
                periodo);

        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
