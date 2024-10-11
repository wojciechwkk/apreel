package apreel.exchange

import java.math.BigDecimal

interface ExchangeFacade {

    fun exchange(exchangeRequest: ExchangeRequest) : BigDecimal
}