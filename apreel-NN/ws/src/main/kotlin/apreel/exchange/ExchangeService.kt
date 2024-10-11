package apreel.exchange


import com.jayway.jsonpath.JsonPath
import jakarta.resource.ResourceException
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import java.net.URL


val CURRENCY_PLN : Currency = Currency.getInstance("PLN")
val CURRENCY_USD : Currency = Currency.getInstance("USD")
class ExchangeService : ExchangeFacade {

    private val baseNBP = "https://api.nbp.pl/api"
    private val supportedCurrencies = arrayOf(CURRENCY_PLN, CURRENCY_USD,)

    override fun exchange(exchangeRequest: ExchangeRequest) : BigDecimal {
        val amount = exchangeRequest.amount
        val from = castCurrency(exchangeRequest.from)
        val to = castCurrency(exchangeRequest.to)

        if( from !in supportedCurrencies || to !in supportedCurrencies || from == to)
            throw ResourceException("$from and $to pair not supported conversion")

//        in case we support other PLN pairs (PLN<->EUR, etc.)
        val currency = if(from != CURRENCY_PLN) from else to
        val rate = callNBP(currency.currencyCode)

//        this is not beautiful, better solution would be to get a different call based on the direction
        return if(from == CURRENCY_PLN) amount.multiply(rate).round()
            else amount.divide(rate,2, RoundingMode.HALF_EVEN)
    }

    private fun BigDecimal.round() =
        this.setScale(2, RoundingMode.HALF_EVEN)

    private fun callNBP(currency: String) : BigDecimal {
         val nbpResponse = try {
            URL("${baseNBP}/exchangerates/rates/a/$currency?format=json")
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            throw ResourceException("error on fetching the $currency rate from $baseNBP")
        }

        val rate = try {
            val nbpJson = JsonPath.parse(nbpResponse)
            BigDecimal.valueOf(nbpJson.read<Double>("rates[0].mid"))
        }
        catch (e: Exception ){
            throw ResourceException("$baseNBP rate format invalid")
        }
        return rate
    }

    private fun castCurrency(currency: String) : Currency {
        return try {
            Currency.getInstance( currency )
        }
        catch (e: IllegalArgumentException) {
            throw ResourceException("$currency not recognized currency")
        }
    }

}