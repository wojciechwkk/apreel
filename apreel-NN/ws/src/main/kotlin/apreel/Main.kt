package apreel

import apreel.account.Account
import apreel.account.AccountRepository
import apreel.account.AccountResource
import apreel.account.AccountService
import apreel.exchange.ExchangeResource
import apreel.exchange.ExchangeService
import apreel.sanity.SanityResource
import apreel.sanity.SanityService
import jakarta.ws.rs.core.UriBuilder
import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.server.ResourceConfig

fun main() {
    println("Hello World!")

    val baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build()
    val config = ResourceConfig()
        .register(SanityResource(SanityService()))
        .register(AccountResource(AccountService(AccountRepository())))
        .register(ExchangeResource(ExchangeService()))

    val server = JettyHttpContainerFactory.createServer(baseUri, config)
    server.
    server.join()
}
