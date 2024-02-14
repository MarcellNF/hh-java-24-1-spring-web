package de.neuefische.hhjava241springweb;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cats")
public class CatController {
    private List<Cat> cats = new ArrayList<>();

    public CatController() {
        cats.add(new Cat("Harry", "Persian Cat"));
        cats.add(new Cat("Hermione", "Persian Cat"));
    }

    @GetMapping
    public List<Cat> getAllCats() {
        return cats;
    }

    @GetMapping("/search")
    public Cat getCatByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String breed
    ) {
        for (Cat cat : cats) {
            if (cat.name().equals(name)) {
                return cat;
            }
            if (cat.breed().equals(breed)) {
                return cat;
            }
        }
        throw new NoSuchElementException();
        //return cats.stream().filter(cat -> cat.breed().equals(breed)).toList();
    }

    @PostMapping
    public List<Cat> addCat(@RequestBody Cat cat) {
        cats.add(cat);
        return cats;
    }

}
