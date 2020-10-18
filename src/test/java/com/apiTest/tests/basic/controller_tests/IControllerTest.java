package com.apiTest.tests.basic.controller_tests;

import java.io.IOException;

public interface IControllerTest {

    void test001_add_200() throws IOException;

    void test002_getList_200() throws IOException;

    void test003_getById_200() throws IOException;

    void test004_editItem_200() throws IOException;

    void test005_getChangedItemById_200() throws IOException;

    void test006_removeById_200() throws IOException;

    void test007_statusBeforeAndAfterTests() throws IOException;
}
