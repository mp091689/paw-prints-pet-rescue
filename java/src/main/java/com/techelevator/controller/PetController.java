package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PetController {
    private PetDao petDao;
    public PetController(PetDao petDao) {
        this.petDao = petDao;
    }
    @GetMapping("/pets")
    public List<Pet> getPets(){
        return petDao.getPets();
    }
//    @RequestMapping(path = "/todos/{id}", method = RequestMethod.GET)
//    public String get(@PathVariable int id) {
//        return todoDao.getTodoById(id);
//    }
//
//    @RequestMapping(path = "/todos", method = RequestMethod.POST)
//    public String add(@RequestBody String todo) {
//        return todoDao.createTodo(todo);
//    }

}
