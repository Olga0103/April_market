package ru.gb.april.market.april_market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.error_handlings.ResourceNotFoundException;
import ru.gb.april.market.april_market.models.Category;
import ru.gb.april.market.april_market.services.CategoryService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getOneCategoryById(@PathVariable Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category doesn't exists: " + id));
    }
}
