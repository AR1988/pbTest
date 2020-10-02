package apiTest.tests.basic.conroller_secured;


import java.io.IOException;

public interface ISecuredTest {


    void testSecuredEndpointAdd() throws IOException;


    void testSecuredEndpointEdit() throws IOException;


    void testSecuredEndpointGet() throws IOException;


    void testSecuredEndpointDelete() throws IOException;


    void testSecuredEndpointGetAll() throws IOException;


    void testSecuredEndpointAddExpiredToken() throws IOException;


    void testSecuredEndpointEditExpiredToken() throws IOException;


    void testSecuredEndpointGetExpiredToken() throws IOException;


    void testSecuredEndpointDeleteExpiredToken() throws IOException;


    void testSecuredEndpointGetAllExpiredToken() throws IOException;
}
