package apreel.account

import java.math.BigDecimal
import java.util.Currency

data class CreateCurrencyAccountRequest (
    val customerFirstName: String,
    val customerLastName: String,
    val balance: BigDecimal,
    val currency: Currency,
)