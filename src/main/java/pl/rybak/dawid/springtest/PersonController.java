package pl.rybak.dawid.springtest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @GetMapping("/person")
    public List<Person> getAllPersons(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person(1,"Mickey Mouse",30));
        personList.add(new Person(2,"Donald Duck",35));
        personList.add(new Person(3,"Peppa Pig",15));
        return personList;
    }

    @GetMapping("/person/{personId}")
    public Person getPersonWithId(@PathVariable Integer personId){
        return new Person(3,"Peppa Pig",15);
    }

    @PostMapping("/person/new-person")
    public void addPerson(@RequestBody Person person){
        System.out.println("Saving person information");
        
    }
}
