package com.ironhack.Midterm.Project.service.user.interfaces;

import com.ironhack.Midterm.Project.model.users.ThirdParty;

public interface ThirdPartyService {
    void update(Long id, ThirdParty thirdParty);
    void delete(Long id);
}
