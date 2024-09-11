package com.example.nermanapp.controller;

import com.example.nermanapp.dto.Request.CategoryRequest.CreateCategoryRequest;
import com.example.nermanapp.dto.Response.CategoryResponse.CategoryResponse;
import com.example.nermanapp.model.Category;
import com.example.nermanapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
//    @PreAuthorize("hasAuthority('admin:read')")
    public List<Category> getAllUsers() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin:read')")
    public Optional<Category> getCategoryByID(@PathVariable int id) {
        return categoryService.getCategoryByID(id);
    }
    @PostMapping("")
    //    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<CategoryResponse> updateUser(
            @PathVariable int id,
            @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteFood(@PathVariable int id) {
        CategoryResponse response = categoryService.deleteCategory(id);
        return ResponseEntity.ok(response);
    }
}
