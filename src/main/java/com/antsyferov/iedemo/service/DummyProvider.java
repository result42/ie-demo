package com.antsyferov.iedemo.service;

import com.antsyferov.iedemo.model.Dummy;

import java.util.List;

public interface DummyProvider {
    List<Dummy> fetchReversed();
}
