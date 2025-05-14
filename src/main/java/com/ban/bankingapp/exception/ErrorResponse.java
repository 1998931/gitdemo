package com.ban.bankingapp.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message,
                            String details,
                            String errorCode) {

}
