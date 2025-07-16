package br.com.mirante.sge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API do Sistema de Gerenciamento de Eventos (SGE)", version = "v1.0.0", description = "Documentação da API do SGE", contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Edilson", email = "edilsonbsfilho@gmail.com"), license = @io.swagger.v3.oas.annotations.info.License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")), servers = {
		@Server(url = "http://localhost:8080", description = "Servidor Local"),
		@Server(url = "https://sua-api.com", description = "Servidor de Produção") })
public class OpenApiConfig {

}