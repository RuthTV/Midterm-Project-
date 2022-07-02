package com.ironhack.Midterm.Project.controller.user.impl;

import com.ironhack.Midterm.Project.controller.user.interfaces.ThirdPartyController;
import com.ironhack.Midterm.Project.model.users.ThirdParty;
import com.ironhack.Midterm.Project.repositories.userRepository.ThirdPartyRepository;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import com.ironhack.Midterm.Project.service.user.interfaces.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ThirdPartyControllerImpl implements ThirdPartyController {
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private ThirdPartyService thirdPartyService;

    @GetMapping("/thirdPartys")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return thirdPartyRepository.findAll();
    }

    @GetMapping("/thirdPartys/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty findById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "id") Long id) {
        Optional<ThirdParty> optionalThirdParty = thirdPartyRepository.findById(id);
        return optionalThirdParty.get();
    }

    @PostMapping("/thirdPartys")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty store(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }

    @PutMapping("/thirdPartys/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody @Valid ThirdParty thirdParty) {
        thirdPartyService.update(id, thirdParty);
    }
    @DeleteMapping("/thirdPartys/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        thirdPartyService.delete(id);
    }
}
