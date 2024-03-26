package com.octanner.graphql101.graphql;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.ResultPath;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		Throwable t = NestedExceptionUtils.getMostSpecificCause(ex);
		ResultPath path = env.getExecutionStepInfo().getPath();

		String message = String.format("Exception while fetching data (%s) : %s", path, t.getMessage());

		return GraphqlErrorBuilder
                .newError(env)
                .message(message)
                .location(env.getField().getSourceLocation())
                .build();
	}
}