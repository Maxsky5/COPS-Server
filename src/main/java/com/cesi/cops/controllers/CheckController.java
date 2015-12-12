package com.cesi.cops.controllers;

import com.cesi.cops.dto.CheckDto;
import com.cesi.cops.entities.Check;
import com.cesi.cops.entities.Cop;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.repositories.CheckRepository;
import com.cesi.cops.repositories.CopRepository;
import com.cesi.cops.repositories.OffenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class CheckController {

    @Autowired
    private OffenderRepository offenderRepository;

    @Autowired
    private CopRepository copRepository;

    @Autowired
    private CheckRepository checkRepository;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<Void> addCheck(
        @Valid @RequestBody ArrayList<CheckDto> checks
    ) {
        for (CheckDto checkDto : checks) {
            Offender offender = offenderRepository.findOne(checkDto.getStudentId());
            Cop cop = copRepository.findOneByMacAddress(checkDto.getCopMacAddress());

            if (null == cop) {
                return ResponseEntity.badRequest().header("Failure", "Cop not found").build();
            }

            if (null == offender) {
                return ResponseEntity.badRequest().header("Failure", "Offender not found").build();
            }

            Check check = new Check();
            check.setDate(checkDto.getDate());
            check.setOffender(offender);
            check.setCop(cop);

            checkRepository.save(check);
        }

        return ResponseEntity.ok().build();
    }
}
