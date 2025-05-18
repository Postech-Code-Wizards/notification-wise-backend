package br.com.wise.notification.domain.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZonedDateTimeReadConverterTest {

    private ZonedDateTimeReadConverter zonedDateTimeReadConverter;

    @BeforeEach
    void setUp() {
        zonedDateTimeReadConverter = new ZonedDateTimeReadConverter();
    }

    @Test
    @DisplayName("Should convert Date to ZonedDateTime in UTC successfully")
    void shouldConvertDateToZonedDateTimeSuccessfully() {
        Date date = create(Date.class);

        ZonedDateTime result = zonedDateTimeReadConverter.convert(date);

        assertEquals(date.toInstant().atZone(ZoneOffset.UTC), result);
    }
}