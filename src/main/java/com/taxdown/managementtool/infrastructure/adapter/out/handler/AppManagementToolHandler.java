package com.taxdown.managementtool.infrastructure.adapter.out.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
public class AppManagementToolHandler  implements RequestHandler<Object, String> {
    @Override
    public String handleRequest(Object input, Context context) {
        return "Hi all !, App Management Tool is running!";
    }
}