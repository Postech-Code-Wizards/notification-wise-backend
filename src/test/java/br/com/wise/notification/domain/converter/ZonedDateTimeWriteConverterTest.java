package br.com.wise.notification.domain.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZonedDateTimeWriteConverterTest {

    private ZonedDateTimeWriteConverter zonedDateTimeWriteConverter;

    @BeforeEach
    void setUp() {
        zonedDateTimeWriteConverter = new ZonedDateTimeWriteConverter();
    }

    @Test
    @DisplayName("Should convert ZonedDateTime to Date successfully")
    void shouldConvertZonedDateTimeToDateSuccessfully() {
        ZonedDateTime zonedDateTime = create(ZonedDateTime.class);

        Date result = zonedDateTimeWriteConverter.convert(zonedDateTime);

        assertEquals(Date.from(zonedDateTime.toInstant()), result);
    }
}