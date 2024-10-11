package apreel.account

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("account")
@Produces(MediaType.APPLICATION_JSON)
class AccountResource(private val accountService: AccountService) : AccountFacade {

    @POST
    @Path("/create")
    override fun createCurrencyAccount(createCurrencyAccountRequest: CreateCurrencyAccountRequest): String {
        return accountService.createCurrencyAccount(createCurrencyAccountRequest)
    }

    @GET
    @Path("/{accountID}")
    override fun getAccount(@PathParam("accountID") accountID: String): Account? =
        accountService.getAccount(accountID)

}