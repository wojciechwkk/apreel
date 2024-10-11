package apreel.exchange

import java.math.BigDecimal

data class ExchangeRequest(
    val amount: BigDecimal,
    val from: String,
    val to: String,
    )
