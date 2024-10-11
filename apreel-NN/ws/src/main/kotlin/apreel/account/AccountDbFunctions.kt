package apreel.account

import java.sql.ResultSet
import java.util.*

object AccountDbFunctions {

    private const val TABLE_NAME = "accounts"
    private const val ALL_COLUMNS = "id, currency, owner_id, balance"

    fun select(id: UUID): String =
        """select $ALL_COLUMNS from $TABLE_NAME where id={$id}")""".trimMargin()


    fun insert(account: Account): String=
        """insert into $TABLE_NAME ($ALL_COLUMNS) values 
            |(${account.id}, ${account.currency}, ${account.owner}, ${account.balance})""".trimMargin()

    fun toAccount(rs: ResultSet): Account =
        Account(
            id = UUID.fromString(rs.getString("id")),
            currency = rs.getString("currency"),
            owner = UUID.fromString(rs.getString("owner_id")),
            balance = rs.getBigDecimal("balance"),
        )
}