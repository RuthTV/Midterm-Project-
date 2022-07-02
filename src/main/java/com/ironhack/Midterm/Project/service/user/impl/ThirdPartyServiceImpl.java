package com.ironhack.Midterm.Project.service.user.impl;

import com.ironhack.Midterm.Project.model.users.ThirdParty;
import com.ironhack.Midterm.Project.repositories.userRepository.ThirdPartyRepository;
import com.ironhack.Midterm.Project.service.user.interfaces.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public void update(Long id, ThirdParty thirdParty) {
        ThirdParty thirdParty1 = thirdPartyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party not found"));
        thirdParty.setId(id);
        thirdPartyRepository.save(thirdParty);
    }
    public void delete(Long id) {
        ThirdParty thirdParty = thirdPartyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party not found"));
        thirdPartyRepository.delete(thirdParty);
    }
}
