package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.SocialMediaService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    SocialMediaService sms;
    public SocialMediaController() {
        sms = new SocialMediaService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("register", this::createAccount);
        app.post("login", this::verifyAccount);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    private void createAccount(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        Account account = om.readValue(ctx.body(), Account.class);
        if (account.getUsername()=="" || account.getPassword().length()<4)
            ctx.status(400);
        else {
            Account verifiedAccount = sms.verifyAccount(account);
            if (verifiedAccount!=null) {//account already exists
                ctx.status(400);
            } else {
                Account createdAccount = sms.createAccount(account);
                ctx.json(om.writeValueAsString(createdAccount));
                ctx.status(200);
            }
        }
    }
    private void verifyAccount(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account verified = sms.verifyAccount(account);
        if (verified==null) {
            ctx.status(401);
        } else {
            ctx.json(om.writeValueAsString(verified));
            ctx.status(200);
        }
    }

}