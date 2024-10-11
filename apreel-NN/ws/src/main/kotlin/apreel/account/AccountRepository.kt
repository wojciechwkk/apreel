package apreel.account

import jakarta.transaction.TransactionManager
import java.util.*

class AccountRepository() {


    fun create(account: Account): Boolean =
        TODO()
//        AccountDbFunctions.insert(account)

    fun find(id: UUID): Account? =
        TODO()
//        AccountDbFunctions.select(id))
//            .mapFirstOrNull(::toAccount)

}