package cielo.spock

import com.cielo.desafio.config.ApplicationProperties;
import com.cielo.desafio.config.response.WebPartialConfig
import com.cielo.desafio.gateway.LancamentoLegadoRepository
import com.cielo.desafio.gateway.impl.LancamentoLegadoRepositoryImpl
import com.cielo.desafio.http.dto.PeriodoDTO
import com.cielo.desafio.http.util.JsonHelper
import com.cielo.desafio.usecase.impl.ExtratoConverter
import com.cielo.desafio.usecase.ExtratoService
import com.cielo.desafio.usecase.impl.ExtratoServiceImpl
import spock.lang.Specification
import spock.lang.*

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static org.assertj.core.api.Assertions.assertThat

@Unroll
@Narrative('Consulta de Extratos de Clientes')
@Title('Consulta de Extratos por filtros definidos')
class ExtratoServiceSpecTest extends Specification {
    private JsonHelper jsonHelper = new JsonHelper(new WebPartialConfig().getObjectMapper())
    private LancamentoLegadoRepository lancamentoLegadoRepository = new LancamentoLegadoRepositoryImpl(jsonHelper)
    private ExtratoConverter extratoConverter = new ExtratoConverter()
    private ApplicationProperties applicationProperties = new ApplicationProperties()
    private ExtratoService extratoService = new ExtratoServiceImpl(lancamentoLegadoRepository, extratoConverter, applicationProperties)

    def "Consultar o extrato de um cliente em um período"() {

        given: "Dado um período determinado, e um CNPJ de um cliente"

        def dataInicial = LocalDate.parse("02/06/2016", DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        def dataFinal = LocalDate.parse("03/06/2016", DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        def periodo = new PeriodoDTO()
        periodo.setDataInicial(dataInicial)
        periodo.setDataFinal(dataFinal)

        when: "quando for feita a solicitacao de consulta de extrato"
        def consultaExtrato = extratoService.findByCnpjAndDataLancamentoBetween("12996721001597", periodo)

        then: "será gerado o extrato do cliente"

        assertThat(consultaExtrato.size()).isEqualTo(2)
        assertThat(consultaExtrato.get(0).cnpj).isEqualTo("12996721001597")
    }
}
