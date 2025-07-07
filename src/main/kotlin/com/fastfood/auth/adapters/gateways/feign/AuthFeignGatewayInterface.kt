package tech.challenge.fastfood.fastfood.adapters.gateways.feign

import com.fastfood.auth.common.dto.request.AuthFeignRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import tech.challenge.fastfood.fastfood.common.dto.response.AuthFeignResponse

@FeignClient(name = "authFeignClient", url = "\${AUTH_SERVICE_URL}")
interface AuthFeignGatewayInterface {

    @PostMapping("/auth", consumes = ["application/json"])
    fun authenticate(@RequestBody authRequest: AuthFeignRequest): AuthFeignResponse
}
