package com.excilys.computer_database.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

  public static void main(String[] args) {

    Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld1");
    logger.debug("Hello world.");

  }
}