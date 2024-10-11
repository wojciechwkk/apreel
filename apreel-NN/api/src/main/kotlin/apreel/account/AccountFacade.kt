package apreel.account

interface AccountFacade {

    fun createCurrencyAccount(createCurrencyAccountRequest: CreateCurrencyAccountRequest) : String

    fun getAccount(accountID: String): Account?

}