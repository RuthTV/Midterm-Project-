package com.ironhack.Midterm.Project.controller.user.interfaces;

import com.ironhack.Midterm.Project.model.users.ThirdParty;

import java.util.List;

public interface ThirdPartyController {
    List<ThirdParty> findAll();
    ThirdParty findById(Long id);
    ThirdParty store(ThirdParty thirdParty);
    void update(Long id, ThirdParty thirdParty);
    void delete(Long id);
}
