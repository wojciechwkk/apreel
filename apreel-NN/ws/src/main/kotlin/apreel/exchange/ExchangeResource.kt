package apreel.exchange

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import java.math.BigDecimal

@Path("exchange")
@Produces(MediaType.APPLICATION_JSON)
class ExchangeResource(private val exchangeService: ExchangeService) : ExchangeFacade {

    @GET
    override fun exchange(exchangeRequest: ExchangeRequest) : BigDecimal =
        exchangeService.exchange(exchangeRequest)
}