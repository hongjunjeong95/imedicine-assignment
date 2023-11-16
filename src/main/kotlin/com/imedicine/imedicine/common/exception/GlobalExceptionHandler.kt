package com.imedicine.imedicine.common.exception

import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import org.springframework.security.access.AccessDeniedException
import mu.KotlinLogging
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ServerException::class)
    fun handleServerException(ex: ServerException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(ex.code).body(ErrorResponse(code = ex.code, message = ex.message))
    }

    @ExceptionHandler(SignatureVerificationException::class)
    fun handleSignatureVerificationException(ex: SignatureVerificationException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(code = 401, message = "Token is not verified"))
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(code = 400, message = "잘못된 요청입니다."))
    }

    @ExceptionHandler(JWTDecodeException::class)
    fun handleJWTDecodeException(ex: JWTDecodeException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(code = 401, message = "Token is null"))
    }

    @ExceptionHandler(TokenExpiredException::class)
    fun handleTokenExpiredException(ex: TokenExpiredException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(code = 401, message = "Token Expired Error"))
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse(code = 403, message = ex.message ?: "Forbidden"))
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDBUKException(ex: DataIntegrityViolationException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(code = 400, message = ex.message ?: "중복 데이터 허용 불가"))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidRequestException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }
        val errors = HashMap<String, String>()
        ex.fieldErrors.forEach { errors[it.field] = it.defaultMessage!! }

        return ResponseEntity.badRequest().body(ErrorResponse(code = 400, message = errors))
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleInvalidRequestException(ex: ResponseStatusException): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(code = 404, message = "데이터를 찾지 못 했습니다."))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error { ex.message }
        logger.error { ex }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse(code = 500, message = "Internal Server Error"))
    }
}