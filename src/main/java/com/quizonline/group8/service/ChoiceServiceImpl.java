package com.quizonline.group8.service;

import com.quizonline.group8.model.Choise;
import com.quizonline.group8.repository.ChoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceServiceImpl implements  ChoiceService{
    @Autowired
    private ChoiceRepo choiceRepo;
    public Choise createAnswer(Choise choise) {
        return choiceRepo.save(choise);
    }
}
