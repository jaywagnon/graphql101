package com.octanner.graphql101.graphql;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLQueryResolver {

    @QueryMapping
    public Boolean gql101HealthCheck() {
        return true;
    }
}
