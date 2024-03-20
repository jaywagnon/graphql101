package com.octanner.graphql101.graphql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GraphQLQueryResolver {

    @QueryMapping
    public Boolean gql101HealthCheck() {
        return true;
    }
}
