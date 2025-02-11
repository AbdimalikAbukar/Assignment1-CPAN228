package com.example.Assignment1_CPAN228.data;

import java.util.Optional;

import com.example.Assignment1_CPAN228.model.Item;

public interface ItemRepository {
    Iterable<Item> findAll();

    Optional<Item> findById(Long id);

    Item save(Item item);

}
