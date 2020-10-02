package apiTest.tests.basic.conroller_secured;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public interface ISecuredTestContact {

    void testSecuredEndpointAdd() throws IOException;

    void testSecuredEndpointEdit() throws IOException;

    void testSecuredEndpointGet() throws IOException;

    void testSecuredEndpointDelete() throws IOException;

    void testSecuredEndpointGetAll() throws IOException;

    void testSecuredEndpointAddExpiredToken() throws IOException;

    void testSecuredEndpointEditExpiredToken() throws IOException;

    void testSecuredEndpointGetExpiredToken() throws IOException;

    void testSecuredEndpointDeleteExpiredTokens() throws IOException;

    void testSecuredEndpointGetAllExpiredTokens() throws IOException;

    void testSecuredEndpointGetProfileExpiredTokens() throws IOException;

    void testSecuredEndpointEditProfileExpiredTokens() throws IOException;

    void testSecuredEndpointAddToContact_contactNotExist_500() throws IOException, LoginException;
}
