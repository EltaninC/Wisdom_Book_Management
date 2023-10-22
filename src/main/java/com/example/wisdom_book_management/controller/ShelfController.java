package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Shelf;
import com.example.wisdom_book_management.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shelf")
public class ShelfController {
    @Autowired
    ShelfService shelfService;
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result selectShelf(){
        return shelfService.selectShelf();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result insertShelf(@RequestBody Shelf shelf){
        return shelfService.insertShelf(shelf);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updateShelf(@RequestBody Shelf shelf){
        return shelfService.updateShelf(shelf);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result updateShelf(String shelf_code){
        return shelfService.deleteShelf(shelf_code);
    }
}
