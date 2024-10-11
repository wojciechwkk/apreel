package kotlin.apreel.exchange

import apreel.exchange.ExchangeRequest
import apreel.exchange.ExchangeService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ExchangeServiceTest {
    private val exchangeService = ExchangeService()

    @Test
    fun `should exchange OK`() {
        val response = exchangeService.exchange(
            ExchangeRequest(
                BigDecimal(55),
                "PLN",
                "USD",
            )
        )

        Assertions.assertEquals(response, BigDecimal("216.0"))
    }
}