
#MicroService - Desafio Cielo
###Construir Docker image: mvn clean package docker:build
### 

##Tecnologias utilizadas:

###Desenvolvimento
Java 8

SpringBoot

SpringFox/Swagger

###Testes
Jacoco - para testes de cobertura - Relatório gerado em: target/site/jacoco/index.html

Spock - para testes de BDD - Relatório gerado em: target/spock/index.html

JUnit - Teste Unitários

Mockito - Mock

MockMvc - Testes Integrados


##Arquitetura:

Clean architecture
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)

##Utilização:

http://localhost:8080/api/extrato/12996721001597 

(POST)

Request Body
```
{
    "dataInicial": "02/06/2016",
    "dataFinal": "03/06/2016"
}
```
Response Body
```
[
  {
    "dataLancamento": "02/06/2016",
    "nomeTipoOperacao": "regular",
    "numeroRemessa": 64320626054,
    "nomeSituacaoRemessa": "Pago",
    "dataConfirmacao": "02/06/2016",
    "dadosBancarios": {
      "codigoBanco": 123,
      "numeroAgencia": 1,
      "numeroContaCorrente": "00000000065470",
      "nomeBanco": "BANCO ABCD S.A."
    },
    "valorLancamentoRemessa": 1960,
    "cnpj": "12996721001597"
  },
  {
    "dataLancamento": "03/06/2016",
    "nomeTipoOperacao": "regular",
    "numeroRemessa": 64320236054,
    "nomeSituacaoRemessa": "Pago",
    "dataConfirmacao": "03/06/2016",
    "dadosBancarios": {
      "codigoBanco": 123,
      "numeroAgencia": 1,
      "numeroContaCorrente": "00000000065470",
      "nomeBanco": "BANCO ABCD S.A."
    },
    "valorLancamentoRemessa": 11499.1,
    "cnpj": "12996721001597"
  },
  {
    "dataLancamento": "04/06/2016",
    "nomeTipoOperacao": "regular",
    "numeroRemessa": 64320626054,
    "nomeSituacaoRemessa": "Pago",
    "dataConfirmacao": "04/06/2016",
    "dadosBancarios": {
      "codigoBanco": 123,
      "numeroAgencia": 1,
      "numeroContaCorrente": "00000000065470",
      "nomeBanco": "BANCO ABCD S.A."
    },
    "valorLancamentoRemessa": 2060,
    "cnpj": "12996721001597"
  }
]
```
