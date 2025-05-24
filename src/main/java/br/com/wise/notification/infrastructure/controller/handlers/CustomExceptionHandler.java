package br.com.wise.notification.infrastructure.controller.handlers;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

}