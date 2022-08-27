package dev.cleysonph.booktracker.api.v1.author.routes;

import dev.cleysonph.booktracker.api.v1.common.routes.ApiV1Routes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorRoutes {

    public static final String CREATE_AUTHOR_ROUTE = ApiV1Routes.BASE_ROUTE + "/authors";

}
