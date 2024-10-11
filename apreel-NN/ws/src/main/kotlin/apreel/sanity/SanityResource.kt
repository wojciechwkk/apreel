package apreel.sanity

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("sanity")
@Produces(MediaType.APPLICATION_JSON)
class SanityResource(private val sanityService: SanityService) {

    @GET
    @Path("/ping")
    fun ping(): String = sanityService.ping()
}