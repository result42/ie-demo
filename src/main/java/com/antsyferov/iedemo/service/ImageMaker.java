package com.antsyferov.iedemo.service;

import java.io.IOException;

public interface ImageMaker {

    byte[] makeImage(String text) throws IOException;
}
