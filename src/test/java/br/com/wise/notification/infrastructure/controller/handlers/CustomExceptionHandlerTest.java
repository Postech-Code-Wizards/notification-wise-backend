package br.com.wise.notification.infrastructure.controller.handlers;

import graphql.GraphQLError;
import graphql.execution.ExecutionStepInfo;
import graphql.execution.ResultPath;
import graphql.language.Field;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomExceptionHandlerTest {

    @Mock
    private DataFetchingEnvironment env;

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    void testResolveToSingleError_ShouldReturnGraphQLError() {
        String errorMessage = Instancio.create(String.class);
        Exception exception = new RuntimeException(errorMessage);

        SourceLocation location = new SourceLocation(1, 1);
        ExecutionStepInfo executionStepInfo = mock(ExecutionStepInfo.class);
        Field field = mock(Field.class);

        when(env.getField()).thenReturn(field);
        when(env.getField().getSourceLocation()).thenReturn(location);
        when(env.getExecutionStepInfo()).thenReturn(executionStepInfo);
        when(executionStepInfo.getPath()).thenReturn(ResultPath.fromList(List.of("fieldName")));

        GraphQLError error = customExceptionHandler.resolveToSingleError(exception, env);

        assertNotNull(error);
        assertEquals(errorMessage, error.getMessage());
        assertEquals(List.of("fieldName"), error.getPath());
    }
}
