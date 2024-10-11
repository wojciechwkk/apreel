package apreel.account

import jakarta.resource.ResourceException
import java.util.UUID

class AccountService(private val accountRepository: AccountRepository) : AccountFacade {

    override fun createCurrencyAccount(createCurrencyAccountRequest: CreateCurrencyAccountRequest): String {
        val accountID = UUID.randomUUID()
        val balance = createCurrencyAccountRequest.balance

//        this is extremely simplified to create owners UUID and invalid, we should fetch customer by his name, based on the tasks requirements,
//        or rather just know who is logged in, but this would mean building the whole "Customer" part of the application:
        val ownerID = UUID.randomUUID()
        val account = Account(
            id = accountID,
            owner = ownerID,
            currency = createCurrencyAccountRequest.currency.currencyCode,
            balance = balance,
        )

        try {
            accountRepository.create(account)
        } catch( e : Exception ){
            throw ResourceException("error creating account for " +
                    "${createCurrencyAccountRequest.customerFirstName} ${createCurrencyAccountRequest.customerLastName}")
        }

        return accountID.toString()
    }


    override fun getAccount(accountID: String): Account? {
        return try {
            accountRepository.find(UUID.fromString(accountID))
        } catch( e : Exception ){
            throw ResourceException("error fetching account $accountID")
        }
    }

}