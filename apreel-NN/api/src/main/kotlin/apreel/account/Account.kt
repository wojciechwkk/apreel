package apreel.account

import java.math.BigDecimal
import java.util.*

data class Account (
    val id: UUID,
    val currency: String,
    val owner: UUID,
    var balance: BigDecimal,
)