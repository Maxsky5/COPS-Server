package com.cesi.cops.controllers;

import com.cesi.cops.dto.CheckDto;
import com.cesi.cops.dto.CheckResultDto;
import com.cesi.cops.entities.Check;
import com.cesi.cops.entities.Cop;
import com.cesi.cops.entities.Lesson;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.repositories.CheckRepository;
import com.cesi.cops.repositories.CopRepository;
import com.cesi.cops.repositories.LessonRepository;
import com.cesi.cops.repositories.OffenderRepository;
import com.cesi.cops.utils.OffenderTypeEnum;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CheckController {

    @Autowired
    private OffenderRepository offenderRepository;

    @Autowired
    private CopRepository copRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CheckRepository checkRepository;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<List<CheckResultDto>> addCheck(
        @Valid @RequestBody ArrayList<CheckDto> checks
    ) {
        List<CheckResultDto> result = new ArrayList<>();
        for (CheckDto checkDto : checks) {
            CheckResultDto checkResultDto = new CheckResultDto(checkDto);

            Offender offender = offenderRepository.findOne(checkDto.getStudentId());
            Cop cop = copRepository.findOneByMacAddress(checkDto.getCopMacAddress());

            if (null == cop) {
                checkResultDto.setSuccess(false);
                checkResultDto.setError("Cop not found");
                result.add(checkResultDto);
                continue;
            }

            if (null == offender) {
                checkResultDto.setSuccess(false);
                checkResultDto.setError("Offender not found");
                result.add(checkResultDto);
                continue;
            }

            DateTime dateCheck = new DateTime(checkDto.getDate());
            dateCheck = dateCheck.withHourOfDay(0);
            dateCheck = dateCheck.withMinuteOfHour(0);
            dateCheck = dateCheck.withSecondOfMinute(0);

            Lesson lesson;

            if (offender.getType().equals(OffenderTypeEnum.TEACHER)) {
                lesson = lessonRepository.findOneByTeacherAndDateLesson(offender, dateCheck);
            } else {
                lesson = lessonRepository.findOneByGradesAndDateLesson(offender.getGrade(), dateCheck);
            }

            if (null == lesson) {
                checkResultDto.setSuccess(false);
                checkResultDto.setError("No lesson found for this offender");
                result.add(checkResultDto);
                continue;
            }

            if (!lesson.getClassroom().equals(cop.getClassroom())) {
                checkResultDto.setSuccess(false);
                checkResultDto.setError("This offender does not belong to this lesson");
                result.add(checkResultDto);
                continue;
            }

            Check check = new Check();
            check.setDate(checkDto.getDate());
            check.setOffender(offender);
            check.setCop(cop);
            check.setLesson(lesson);

            try {
                checkRepository.save(check);
                checkResultDto.setSuccess(true);
            } catch (Exception e) {
                checkResultDto.setSuccess(false);
                checkResultDto.setError(e.getMessage());
            } finally {
                result.add(checkResultDto);
            }

        }

        return ResponseEntity.ok().body(result);
    }
}
