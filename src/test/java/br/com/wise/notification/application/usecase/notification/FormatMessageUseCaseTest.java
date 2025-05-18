package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormatMessageUseCaseTest {

    private FormatMessageUseCase formatMessageUseCase;

    @BeforeEach
    void setUp() {
        formatMessageUseCase = new FormatMessageUseCase();
    }

    @Test
    @DisplayName("Should replace placeholders in the message template with provided values")
    void testReplaceMessageValuesSuccess() {
        String messageTemplate = "Hello, {name}! Your order {orderId} is ready.";
        Map<String, String> values = Map.of("name", "John", "orderId", "12345");

        String result = formatMessageUseCase.replaceMessageValues(messageTemplate, values);

        assertEquals("Hello, John! Your order 12345 is ready.", result);
    }


    @Test
    @DisplayName("Should throw BusinessException when message contains unresolved placeholders")
    void testReplaceMessageValuesUnresolvedPlaceholders() {
        String messageTemplate = "Hello, {name}! Your order {orderId} is ready.";
        Map<String, String> values = Map.of("name", "John");

        BusinessException exception = assertThrows(BusinessException.class, () ->
                formatMessageUseCase.replaceMessageValues(messageTemplate, values));

        assertEquals("The message contains keys with missing values: {orderId}", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw BusinessException when message contains unresolved placeholders and values are null")
    void testValidateFormatWithUnresolvedPlaceholders() {
        String messageTemplate = "Hello, {name}! Your order {orderId} is ready.";

        BusinessException exception = assertThrows(BusinessException.class, () ->
                formatMessageUseCase.replaceMessageValues(messageTemplate, null));

        assertEquals("The message contains keys with missing values: {name}", exception.getMessage());
    }

    @Test
    @DisplayName("Should return the original message when template is empty")
    void testReplaceMessageValuesEmptyTemplate() {
        String messageTemplate = "";
        Map<String, String> values = Instancio.ofMap(String.class, String.class)
                .size(3)
                .create();

        String result = formatMessageUseCase.replaceMessageValues(messageTemplate, values);

        assertEquals("", result);
    }
}