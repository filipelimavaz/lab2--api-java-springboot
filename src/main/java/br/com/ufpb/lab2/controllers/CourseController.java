package br.com.ufpb.lab2.controllers;

import br.com.ufpb.lab2.dtos.*;
import br.com.ufpb.lab2.exceptions.InvalidIdException;
import br.com.ufpb.lab2.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/")
public class CourseController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<ReturnCourseDTO>> returnAllCourses() {
        return new ResponseEntity<>(disciplinaService.returnAllCourses(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SpecificCourseDTO> returnSpecificCourse(@PathVariable String id) throws InvalidIdException {
        return new ResponseEntity<>(disciplinaService.returnSpecificCourse(id), HttpStatus.OK);
    }

    @PatchMapping("likes/{id}")
    public ResponseEntity<AddLikeDTO> addLike(@PathVariable String id) {
        return new ResponseEntity<>(disciplinaService.addLike(id), HttpStatus.OK);
    }

    @PatchMapping("rate/{id}")
    public ResponseEntity<AddRateDTO> addRate(@PathVariable String id, @RequestBody RateDTO rateDTO) {
        return new ResponseEntity<>(disciplinaService.addRate(id, rateDTO.getRate()), HttpStatus.OK);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<AddCommentDTO> addComment(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(disciplinaService.addComment(id, commentDTO.getComment()), HttpStatus.OK);
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<ReturnCommentDTO> returnComment(@PathVariable String id, @RequestParam(required = false) String filter) {
        return new ResponseEntity<>(disciplinaService.returnComment(id), HttpStatus.OK);
    }

    @PostMapping("{id}/tags")
    public ResponseEntity<ReturnCourseTagDTO> addTag(@PathVariable String id, @RequestBody TagDTO tagDTO) {
            return new ResponseEntity<>(disciplinaService.addTag(id, tagDTO.getName()), HttpStatus.OK);
    }

    @GetMapping("ranking/courses")
    public ResponseEntity<List<CourseDTO>> orderCoursesByAverage() {
        return new ResponseEntity<>(disciplinaService.orderCoursesByAverage(), HttpStatus.OK);
    }

    @GetMapping("ranking/likes")
    public ResponseEntity<List<CourseDTO>> orderCoursesByLike() {
        return new ResponseEntity<>(disciplinaService.orderCoursesByLike(), HttpStatus.OK);
    }

    @GetMapping("{id}/tags")
    public ResponseEntity<List<TagDTO>> returnCourseTags(@PathVariable String id) {
        return new ResponseEntity<>(disciplinaService.returnCourseTags(id), HttpStatus.OK);
    }

    @GetMapping("tags")
    public ResponseEntity<List<ReturnCourseDTO>> returnCoursesByTag(@RequestParam("nome") String nome) {
        return new ResponseEntity<>(disciplinaService.returnCoursesByTag(nome), HttpStatus.OK);
    }
}
