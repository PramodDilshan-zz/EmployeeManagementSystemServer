package com.creative.dil.controller;

import com.creative.dil.model.Employee;
import com.creative.dil.model.Skill;
import com.creative.dil.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;
    private Skill skill;

    SkillController(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @PostMapping(value = "/skills/create")
    public Skill createSkill(@RequestBody Skill skill){
        this.skill = this.skillRepository.save(skill);
        return this.skill;
    }

    @GetMapping(value = "/skills")
    public List<Skill> getAllSkill(){
        List<Skill> skillsList = new ArrayList<>();
        this.skillRepository.findAll().forEach(skillsList::add);
        return skillsList;
    }
}
